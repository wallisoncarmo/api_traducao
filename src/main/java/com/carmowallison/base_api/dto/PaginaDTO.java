package com.carmowallison.base_api.dto;

import com.carmowallison.base_api.domain.Pagina;
import com.carmowallison.base_api.domain.Traducao;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document
public class PaginaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotEmpty(message = "Nome é um campo é Obrigatório!")
    @Length(min = 5, max = 255, message = "O tamanho precisa ser de 5 a 255 caracter!")
    private String nome;

    @DBRef
    private List<Traducao> traducoes;

    public PaginaDTO(){}

    public PaginaDTO(Pagina obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.traducoes = obj.getTraducoes();
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
        PaginaDTO paginaDTO = (PaginaDTO) o;
        return Objects.equals(id, paginaDTO.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
