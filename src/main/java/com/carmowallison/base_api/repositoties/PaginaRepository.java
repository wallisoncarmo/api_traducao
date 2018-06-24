package com.carmowallison.base_api.repositoties;


import com.carmowallison.base_api.domain.Linguagem;
import com.carmowallison.base_api.domain.Pagina;
import com.carmowallison.base_api.domain.Traducao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PaginaRepository extends MongoRepository<Pagina, String> {
    List<Pagina> findByTraducoesIn(List<Traducao> traducoes);
}
