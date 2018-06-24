package com.carmowallison.base_api.dto;


import com.carmowallison.base_api.domain.Linguagem;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Document
public class LinguagemDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotEmpty(message = "Nome é um campo é Obrigatório!")
    @Length(min = 5, max = 255, message = "O tamanho precisa ser de 5 a 255 caracter!")
    private String name;

    public LinguagemDTO(Linguagem obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }
    public LinguagemDTO() { }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinguagemDTO linguagemDTO = (LinguagemDTO) o;
        return Objects.equals(id, linguagemDTO.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
