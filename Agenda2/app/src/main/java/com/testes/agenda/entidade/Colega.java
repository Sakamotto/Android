package com.testes.agenda.entidade;

/**
 * Created by cristian on 30/05/16.
 */
public class Colega extends Contato {
    private String localTrabalho;
    private String profissao;
    private String referencia;

    public void imprimeDados(){
        super.print();
        System.out.println("Profissão: " + this.getProfissao());
        System.out.println("Local de Trabalho: " + this.getLocalTrabalho());
        System.out.println("Referência: " + this.getReferencia());
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
