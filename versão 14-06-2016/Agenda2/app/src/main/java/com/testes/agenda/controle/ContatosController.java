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
    private int id = 0;

    public void adicionar(File dir, Object objSalvar){

        try {
            File arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ArrayList<Contato> contatos = new ArrayList<>();

            if(arquivo.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
                contatos = (ArrayList<Contato>) entrada.readObject();
                entrada.close();
                id = controleId(dir);
            }

            arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));

            ((Contato)objSalvar).setId(id + 1);
            contatos.add((Contato) objSalvar);

            saida.writeObject(contatos);
            saida.close();
            Log.v("Adicionar", "Contato adicionado com sucesso!");

        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }


    }

    public void deletar(File dir, int pos) {
        try {
            File arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ArrayList<Contato> contatos = new ArrayList<>();

            if(arquivo.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
                contatos = (ArrayList<Contato>) entrada.readObject();
                entrada.close();
            }

            arquivo = new File(new File(dir, "") + File.separator + LOCAL);
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));
            contatos.remove(pos);
            saida.writeObject(contatos);
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
            e.printStackTrace();
        }
        return result;
    }

    private int controleId(File dir){
        ArrayList<Contato> listaContatos;
        int lastId = 0;
        Object objEntrada = ler(dir);

        if(objEntrada == null){
            return 0;
        }
        listaContatos = ((ArrayList<Contato>) objEntrada);

        if(listaContatos.isEmpty()){
            return 0;
        }

        lastId = listaContatos.get(listaContatos.size() - 1).getId();

        return lastId;
    }

}
