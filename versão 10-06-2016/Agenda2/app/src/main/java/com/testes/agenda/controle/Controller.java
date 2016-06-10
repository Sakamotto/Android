package com.testes.agenda.controle;

import com.testes.agenda.entidade.Contato;
import com.testes.agenda.entidade.Evento;

import java.io.File;
import java.io.Serializable;

/**
 * Created by cristian on 10/06/16.
 */
public class Controller implements Serializable{

    public static final int EVENTO = 0;
    public static final int CONTATO = 1;

    public static void adicionar(File dir, int tipo, Object salvar){

        if(tipo == EVENTO){
            EventosController eventosController = new EventosController();
            eventosController.adicionar(dir, salvar);
        }else if(tipo == CONTATO){
            ContatosController contatosController = new ContatosController();
            contatosController.adicionar(dir, salvar);

        }

    }
    public static void editar(File dir, int tipo, int pos,Object editar){
        if(tipo == EVENTO){
            EventosController eventosController = new EventosController();
            eventosController.editar(dir, pos, editar);
        }else {
            ContatosController contatosController = new ContatosController();
            contatosController.editar(dir, pos, editar);
        }
    }
    public static void deletar(File dir, int tipo, int pos){
        if(tipo == EVENTO){
            EventosController eventosController = new EventosController();
            eventosController.deletar(dir, pos);
        }else {
            ContatosController contatosController = new ContatosController();
            contatosController.deletar(dir, pos);
        }

    }
    public static Object ler(File dir, int tipo){

        return null;

    }
}
