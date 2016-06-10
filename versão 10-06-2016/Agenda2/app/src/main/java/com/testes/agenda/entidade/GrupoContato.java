package com.testes.agenda.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 08/06/16.
 */
public class GrupoContato implements Serializable {
    Amigo amigo;
    Colega colega;
    Familia familia;
    private List<Relacao> list = new ArrayList<>();

    public void criarAmigo(String referencia){
        amigo = new Amigo();
        amigo.setReferencia(referencia);
    }

    public void criarColega(String profissao){
        colega = new Colega();
        colega.setProfissao(profissao);
    }

    public void criarFamilia(String parentesco){
        familia = new Familia();
        familia.setTipoParentesco(parentesco);
    }

    public void apply(){
        if(amigo != null) getList().add(amigo);
        if(colega != null) getList().add(colega);
        if(familia != null) getList().add(familia);
    }

    public List<Relacao> getList() {
        return list;
    }

    public void setList(List<Relacao> list) {
        this.list = list;
    }
}
