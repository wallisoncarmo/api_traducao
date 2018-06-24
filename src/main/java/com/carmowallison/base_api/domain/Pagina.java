package com.carmowallison.base_api.domain;

import com.carmowallison.base_api.dto.PaginaDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document
public class Pagina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nome;

    @DBRef
    private List<Traducao> traducoes;

    public Pagina(){}

    public Pagina(String id, String nome, List<Traducao> traducoes) {
        this.id = id;
        this.nome = nome;
        this.traducoes = traducoes;
    }

    public Pagina fromDTO(PaginaDTO objDTO) {
        return new Pagina(objDTO.getId(), objDTO.getNome(),objDTO.getTraducoes());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Traducao> getTraducoes() {
        return traducoes;
    }

    public void setTraducoes(List<Traducao> traducoes) {
        this.traducoes = traducoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagina pagina = (Pagina) o;
        return Objects.equals(id, pagina.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
