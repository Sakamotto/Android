package com.testes.agenda.entidade;

/**
 * Created by cristian on 30/05/16.
 */
public class Amigo extends Contato {
    private String referencia;

    public void imprimeDados(){
        super.print();
        System.out.println("Referência: " + this.getReferencia());
    }


    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
