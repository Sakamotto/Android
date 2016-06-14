package com.testes.agenda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.testes.agenda.controle.Controller;
import com.testes.agenda.entidade.Contato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        assert rvListar != null;
        rvListar.setHasFixedSize(true);

        // usar um gerenciador de layout
        lmManager = new LinearLayoutManager(this);
        rvListar.setLayoutManager(lmManager);
        rvListar.setItemAnimator(new DefaultItemAnimator());

        adapter = new MyAdapter(this, Controller.ler(new File(getFilesDir(), ""),Controller.CONTATO));
        rvListar.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        ((MyAdapter) adapter).setOnItemClickListener(new MyAdapter.ControladorCard() {
            @Override
            public void onItemClick(int position, View v) {
                List<Contato> listaContatos = (ArrayList<Contato>) Controller.ler(new File(getFilesDir(), ""),Controller.CONTATO);
                Contato contatoEditado = listaContatos.get(position);
                Intent editarContato = new Intent(getApplicationContext(), EditarContato.class);
                editarContato.putExtra("nome", contatoEditado.getNome());
                editarContato.putExtra("email", contatoEditado.getEmail());
                editarContato.putExtra("telefone", contatoEditado.getCelular());
                editarContato.putExtra("idade", contatoEditado.getIdade());
                editarContato.putExtra("genero", contatoEditado.getGenero());
                editarContato.putExtra("proximidade", contatoEditado.getNivelProximidade());
                editarContato.putExtra("nascimento", contatoEditado.getDataNascimento());
                editarContato.putExtra("indiceContato", position);
                editarContato.putExtra("grupoContato",contatoEditado.getGrupoContato());
                startActivity(editarContato);
            }
        });
    }
}
