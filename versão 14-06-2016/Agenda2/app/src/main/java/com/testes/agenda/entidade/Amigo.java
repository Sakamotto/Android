package com.testes.agenda.entidade;

import java.io.Serializable;

/**
 * Created by cristian on 30/05/16.
 */
public class Amigo extends Relacao implements Serializable{
    private String referencia;

    public void imprimeDados(){
//        super.print();
        System.out.println("Referência: " + this.getReferencia());
    }


    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
