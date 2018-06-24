package com.carmowallison.base_api.repositoties;

import com.carmowallison.base_api.domain.Linguagem;
import com.carmowallison.base_api.domain.Traducao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface  TraducaoRepository extends MongoRepository<Traducao,String> {
    List<Traducao> findByLinguagem(Linguagem linguagem);
}
