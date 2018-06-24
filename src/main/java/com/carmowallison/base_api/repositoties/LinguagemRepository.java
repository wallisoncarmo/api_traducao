package com.carmowallison.base_api.repositoties;


import com.carmowallison.base_api.domain.Linguagem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface  LinguagemRepository extends MongoRepository<Linguagem,String> {
    Linguagem findByNameIgnoreCase(String nome, boolean b);
}
