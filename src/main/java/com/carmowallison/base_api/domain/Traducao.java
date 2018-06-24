package com.carmowallison.base_api.domain;



import com.carmowallison.base_api.dto.TraducaoDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;


@Document
public class Traducao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String campo;
    private String traducao;

    @DBRef
    private Linguagem linguagem;

    public Traducao() {    }

    public Traducao(String id, String campo, String traducao, Linguagem linguagem) {
        this.id = id;
        this.campo = campo;
        this.traducao = traducao;
        this.linguagem = linguagem;
    }

    public Traducao fromDTO(TraducaoDTO objDTO) {
        return new Traducao(objDTO.getId(), objDTO.getCampo(),objDTO.getTraducao(),objDTO.getLinguagem());
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
        Traducao traducao = (Traducao) o;
        return Objects.equals(id, traducao.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
