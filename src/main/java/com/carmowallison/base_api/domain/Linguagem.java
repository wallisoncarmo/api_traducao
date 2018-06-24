package com.carmowallison.base_api.domain;


import com.carmowallison.base_api.dto.LinguagemDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
public class Linguagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;

    public Linguagem(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Linguagem() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Linguagem fromDTO(LinguagemDTO objDTO) {
        return new Linguagem(objDTO.getId(), objDTO.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Linguagem linguagem = (Linguagem) o;
        return Objects.equals(id, linguagem.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
