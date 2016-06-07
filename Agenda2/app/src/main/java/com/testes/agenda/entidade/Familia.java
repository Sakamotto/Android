package com.testes.agenda.entidade;

/**
 * Created by cristian on 30/05/16.
 */
public class Familia extends Contato {
    private String tipoParentesco;

    @Override
    public void imprimeDados() {
        super.print();
        System.out.println("Parentesco: " + this.getTipoParentesco());
    }

    public String getTipoParentesco() {
        return tipoParentesco;
    }

    public void setTipoParentesco(String tipoParentesco) {
        this.tipoParentesco = tipoParentesco;
    }
}
