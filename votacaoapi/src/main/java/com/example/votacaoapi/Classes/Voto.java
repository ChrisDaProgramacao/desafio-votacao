package com.example.votacaoapi.Classes;

import jakarta.persistence.*;

@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Sessao sessao;
    private Long associadoId;
    private boolean voto; // true para "Sim", false para "Não"

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Long getAssociadoId() {
        return associadoId;
    }

    public void setAssociadoId(Long associadoId) {
        this.associadoId = associadoId;
    }

    public boolean isVoto() {
        return voto;
    }

    public void setVoto(boolean voto) {
        this.voto = voto;
    }


}