package com.testes.agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.testes.agenda.entidade.Contato;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 07/06/16.
 */
public class ListarContatos extends AppCompatActivity {

    private RecyclerView rvListar;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lmManager;

    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_listar_contatos);
        rvListar = (RecyclerView) findViewById(R.id.rv_listar_contatos);
        rvListar.setHasFixedSize(true);
        // usar um gerenciador de layout
        lmManager = new LinearLayoutManager(this);
        rvListar.setLayoutManager(lmManager);
        rvListar.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyAdapter(this, abreContato(CadastroContatos.DIR_DAT));
        rvListar.setAdapter(adapter);

    }

    public List<Contato> abreContato(String nomeArq){
        FileInputStream arqLeitura;
        ObjectInputStream objLeitura;
        // Recuperando os Objetos salvos ...
        try{
            arqLeitura = new FileInputStream(getFilesDir() + File.separator + nomeArq);
            objLeitura = new ObjectInputStream(arqLeitura);

            List<Contato> listaContatos = (ArrayList<Contato>)objLeitura.readObject();

            objLeitura.close();
            arqLeitura.close();

            Log.v("Listar Contatos", "Passei aqui");
            Log.v("Listar Contatos", listaContatos.get(0).getNome());


            return listaContatos;

        }catch (Exception e){
            Log.e("Freeza", "Abre Contato");
            Log.e("Freeza", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
