package com.carmowallison.base_api.services;



import com.carmowallison.base_api.domain.Linguagem;
import com.carmowallison.base_api.repositoties.LinguagemRepository;
import com.carmowallison.base_api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LinguagemService {

    @Autowired
    LinguagemRepository repo;

    public Linguagem findById(String id) {
        Optional<Linguagem> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Linguagem insert(Linguagem obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Linguagem update(Linguagem obj) {
        Linguagem newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public List<Linguagem> findAll() {
        return repo.findAll();
    }

    public Page<Linguagem> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    private void updateData(Linguagem newObj, Linguagem obj) {
        if (!obj.getName().equals("")) {
            newObj.setName(obj.getName());
        }
    }

}
