package com.testes.agenda.entidade;

import java.io.Serializable;

/**
 * Created by cristian on 30/05/16.
 */
public class Familia extends Relacao implements Serializable{
    private String tipoParentesco;

    public void imprimeDados() {
        System.out.println("Parentesco: " + this.getTipoParentesco());
    }

    public String getTipoParentesco() {
        return tipoParentesco;
    }

    public void setTipoParentesco(String tipoParentesco) {
        this.tipoParentesco = tipoParentesco;
    }
}
