package com.testes.agenda.controle;

import android.util.Log;
import android.widget.Toast;

import com.testes.agenda.CadastroContatos;
import com.testes.agenda.entidade.Contato;
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
public class ContatosController{
    public static final String LOCAL = "contatos.dat";

    public void adicionar(File dir, Object salvar){

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
            saida.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editar(File dir, int pos, Object editar) {
        try{
            File arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ArrayList<Contato> listaContatos = new ArrayList<>();

            if(arquivo.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
                listaContatos = (ArrayList<Contato>) entrada.readObject();
                entrada.close();
            }

            arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));

            listaContatos.remove(pos);
            listaContatos.add(pos, (Contato) editar);

            saida.writeObject(listaContatos);
            saida.close();
            Log.v("Editar Contato", "CONTATO EDITADO COM SUCESSSO!");

        }catch (Exception e){
            Log.e("Freeza", "Erro ao editar contato");
            e.printStackTrace();
        }
    }
}
