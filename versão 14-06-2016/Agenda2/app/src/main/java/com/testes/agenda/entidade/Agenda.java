package com.testes.agenda.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 30/05/16.
 */
public class Agenda implements Serializable{
    List<Contato> contatos = new ArrayList<>();
    List<Evento> eventos = new ArrayList<>();

    /*
    * Gerência de Contatos
    * Abaixo estão os métodos para lidar com os contatos de uma agenda
    */

    public void criarContato(Contato c) {
        if (c != null) {
            contatos.add(c);
        } else {
            System.out.println("Um erro ocorreu ao inserir o contato, tente novamente!");
        }
    }

    public void editarContato(Contato c) {
        c.imprimeDados();
        // Rotina para editar ... O.o
    }

    public void excluirContato(Contato c) {
        contatos.remove(c);
    }

    public void excluirContato(int codigo) {
        contatos.remove(contatos.get(codigo));
    }

    /*
    Método que busca um contato pelo nome. Se o encontrar, retorna o objeto contato, caso contrário, retorna null
     */
    public Contato pesquisarContato(String nome) {
        for (Contato c : contatos) {
            if (c.getNome().equals(nome)) {
                return c;
            }
        }
        return null;
    }

    public void imprimeTodosContatos(){
        for(Contato c: contatos){
            System.out.println("************************ Dados dos Contatos ************************\n");
            c.imprimeDados();
        }
    }

    /*
    * Gerência de Eventos
    * Abaixo estão os métodos para lidar com os Eventos de uma agenda
    */

    public void criarEvento(Evento e) {
        if (e != null) {
            eventos.add(e);
        } else {
            System.out.println("Um erro ocorreu ao criar um Evento, tente novamente!");
        }
    }

    public void excluirEvento(Evento e){
        if(e != null){
            eventos.remove(e);
        }else{
            System.out.println("Um erro ocorreu ao excluir um Evento, tente novamente!");
        }
    }

    public void excluirEvento(int codigoEvento){
        eventos.remove(eventos.get(codigoEvento));
    }

    public Evento pesquisarEvento(String tituloEvento){
        for(Evento e: eventos){
            if(e.getTitulo().equals(tituloEvento)){
                return e;
            }
        }
        return null;
    }
}
