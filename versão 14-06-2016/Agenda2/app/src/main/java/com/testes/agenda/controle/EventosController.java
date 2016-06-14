package com.testes.agenda.controle;

import android.util.Log;

import com.testes.agenda.entidade.Evento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by cristian on 10/06/16.
 */
public class EventosController{

    public static final String LOCAL = "eventos.dat";

    public void adicionar(File dir, Object objSalvar) {
        try {
            File arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ArrayList<Evento> eventos = new ArrayList<>();

            if(arquivo.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
                eventos = (ArrayList<Evento>) entrada.readObject();
                entrada.close();
            }

            arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));
            eventos.add((Evento) objSalvar);
            saida.writeObject(eventos);
            saida.close();
            Log.v("Adicionar", "Contato adicionado com sucesso!");

        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void editar(File dir, int pos, Object editar) {
        try{
            File arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ArrayList<Evento> listaEventos = new ArrayList<>();

            if(arquivo.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
                listaEventos = (ArrayList<Evento>) entrada.readObject();
                entrada.close();
            }

            arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));

            listaEventos.remove(pos);
            listaEventos.add(pos, (Evento)editar);

            saida.writeObject(listaEventos);
            saida.close();
            Log.v("Editar Contato", "CONTATO EDITADO COM SUCESSSO!");

        }catch (Exception e){
            Log.e("Freeza", "Erro ao editar contato");
            e.printStackTrace();
        }
    }

    public void deletar(File dir, int pos) {
        try {
            File arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ArrayList<Evento> eventos = new ArrayList<>();

            if(arquivo.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
                eventos = (ArrayList<Evento>) entrada.readObject();
                entrada.close();
            }

            arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));
            eventos.remove(pos);
            saida.writeObject(eventos);
            saida.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Object ler(File dir) {
        Object result = new Object();
        try {
            File arquivo = new File(new File(dir, "") + File.separator + LOCAL);

            if (arquivo.exists()) {
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
                result = entrada.readObject();
                entrada.close();
            }

        } catch (Exception e) {
            Log.e("EVENTOS - LER", e.getMessage() + " <<<<");
            e.printStackTrace();
        }
        return result;
    }
}
