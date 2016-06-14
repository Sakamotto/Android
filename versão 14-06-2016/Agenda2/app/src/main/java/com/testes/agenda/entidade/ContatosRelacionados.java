package com.testes.agenda.entidade;

import java.io.Serializable;

/**
 * Created by cristian on 14/06/16.
 */
public class ContatosRelacionados implements Serializable{
    private int id;
    private int proximidade;
    private int relacao;
    private GrupoContato grupoContato; // Grupo de contatos que os contatos se relacionam

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProximidade() {
        return proximidade;
    }

    public void setProximidade(int proximidade) {
        this.proximidade = proximidade;
    }

    public int getRelacao() {
        return relacao;
    }

    public void setRelacao(int relacao) {
        this.relacao = relacao;
    }

    public GrupoContato getGrupoContato() {
        return grupoContato;
    }

    public void setGrupoContato(GrupoContato grupoContato) {
        this.grupoContato = grupoContato;
    }
}
