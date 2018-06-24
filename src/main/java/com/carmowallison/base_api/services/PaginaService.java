package com.carmowallison.base_api.services;


import com.carmowallison.base_api.domain.Linguagem;
import com.carmowallison.base_api.domain.Pagina;
import com.carmowallison.base_api.domain.Traducao;
import com.carmowallison.base_api.repositoties.LinguagemRepository;
import com.carmowallison.base_api.repositoties.PaginaRepository;
import com.carmowallison.base_api.repositoties.TraducaoRepository;
import com.carmowallison.base_api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort.Direction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PaginaService {

    @Autowired
    PaginaRepository repo;
    @Autowired
    TraducaoRepository traducaoRepository;
    @Autowired
    LinguagemRepository linguagemRepository;

    public Pagina findById(String id) {
        Optional<Pagina> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }


    public Pagina insert(Pagina obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Pagina update(Pagina obj) {
        Pagina newObj = findById(obj.getId());

        updateData(newObj, obj);

        return repo.save(newObj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public List<Pagina> findAll() {
        return repo.findAll();
    }


    public Page<Pagina> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);

    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public Pagina saveCSV(MultipartFile file, Pagina pagina, Linguagem linguagem) {

        File newFile = null;
        List<Traducao> traducoes = new ArrayList<Traducao>();
        try {

            newFile = convert(file);
            FileReader reader = new FileReader(newFile);
            BufferedReader in = new BufferedReader(reader);
            String string;

            while ((string = in.readLine()) != null) {
                String line = string;
                String[] values = line.split(",");
                traducoes.add(new Traducao(null, values[0], values[1], linguagem));

            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        traducoes = traducaoRepository.saveAll(traducoes);

        pagina.setTraducoes(traducoes);

        return repo.save(pagina);
    }

    public List<Pagina> findAllPageByLinguagem(String nome) {
        List<Pagina> list = new ArrayList<>();
        Linguagem linguagem = linguagemRepository.findByNameIgnoreCase(nome, false);
        List<Traducao> traducoes = traducaoRepository.findByLinguagem(linguagem);
        return repo.findByTraducoesIn(traducoes);
    }


    private void updateData(Pagina newObj, Pagina obj) {
        if (!obj.getNome().equals("")) {
            newObj.setNome(obj.getNome());
        }
        newObj.setTraducoes(obj.getTraducoes());
    }
}
