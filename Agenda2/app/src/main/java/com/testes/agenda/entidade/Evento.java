package com.testes.agenda.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cristian on 30/05/16.
 */
public class Evento {
    public static final int MAX_PARTICIPANTES = 20;
    public static final int ABERTO = 1;
    public static final int FECHADO = 2;
    public static final int GRANDE = 3;
    public static final int RESERVADO = 4;

    private String titulo;
    private String local;
    private Date data;
    private float valor;
    private int exclusividade; // 1 - Aberto, 2 - Fechado, 3 - Grande, 4 - Reservado
    private List<Gosto> gostos = new ArrayList<Gosto>();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getExclusividade() {
        return exclusividade;
    }

    public void setExclusividade(int exclusividade) {
        this.exclusividade = exclusividade;
    }

    public List<Gosto> getGostos() {
        return gostos;
    }

    public void setGostos(List<Gosto> gostos) {
        this.gostos = gostos;
    }
}
