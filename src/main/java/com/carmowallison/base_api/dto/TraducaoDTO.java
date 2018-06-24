package com.carmowallison.base_api.dto;


import com.carmowallison.base_api.domain.Linguagem;
import com.carmowallison.base_api.domain.Traducao;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;


@Document
public class TraducaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotEmpty(message = "Campo é Obrigatório!")
    @Length(min = 5, max = 255, message = "O tamanho precisa ser de 5 a 255 caracter!")
    private String campo;

    @NotEmpty(message = "Tradução é um campo Obrigatório!")
    @Length(min = 5, max = 255, message = "O tamanho precisa ser de 5 a 255 caracter!")
    private String traducao;

    @DBRef
    private Linguagem linguagem;

    public TraducaoDTO() {
    }

    public TraducaoDTO(Traducao obj) {
        this.id = obj.getId();
        this.campo = obj.getCampo();
        this.traducao = obj.getTraducao();
        this.linguagem = obj.getLinguagem();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getTraducao() {
        return traducao;
    }

    public void setTraducao(String traducao) {
        this.traducao = traducao;
    }

    public Linguagem getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(Linguagem linguagem) {
        this.linguagem = linguagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TraducaoDTO traducaoDTO = (TraducaoDTO) o;
        return Objects.equals(id, traducaoDTO.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
