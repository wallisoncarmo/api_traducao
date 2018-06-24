package com.carmowallison.base_api.services;


import com.carmowallison.base_api.domain.Traducao;
import com.carmowallison.base_api.repositoties.TraducaoRepository;
import com.carmowallison.base_api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TraducaoService {

    @Autowired
    TraducaoRepository repo;

    public Traducao findById(String id) {
        Optional<Traducao> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Traducao insert(Traducao obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Traducao update(Traducao obj) {
        Traducao newObj = findById(obj.getId());

        updateData(newObj, obj);

        return repo.save(newObj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public List<Traducao> findAll() {
        return repo.findAll();
    }

    public Page<Traducao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    private void updateData(Traducao newObj, Traducao obj) {
        if (!obj.getCampo().equals("")) {
            newObj.setCampo(obj.getCampo());
        }

        if (!obj.getTraducao().equals("")) {
            newObj.setTraducao(obj.getTraducao());
        }

        if (obj.getLinguagem()!=null) {
            newObj.setLinguagem(obj.getLinguagem());
        }
    }

}
