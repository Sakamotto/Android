package com.testes.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.testes.agenda.controle.Controller;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by cristian on 14/06/16.
 */
public class CadastroRelacionamentos extends AppCompatActivity {

    private RecyclerView recyclerViewRelacionamentos;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    Object listaContatos;
    ArrayList<Integer> listaIds = new ArrayList<>();
    int pos;

    CheckBox cbRelacionamento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacionamentos);

        cbRelacionamento = (CheckBox) findViewById(R.id.cbRelacionamento);

        recyclerViewRelacionamentos = (RecyclerView) findViewById(R.id.rv_relacionamentos);
        recyclerViewRelacionamentos.setHasFixedSize(true);
        recyclerViewRelacionamentos.setLayoutManager(new LinearLayoutManager(this));
        listaContatos = Controller.ler(new File(getFilesDir(), ""),Controller.CONTATO);
        adapter = new AdapterRelacionamentos(listaContatos, listaIds);
        recyclerViewRelacionamentos.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        listaIds = ((AdapterRelacionamentos)adapter).getListaIds();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((AdapterRelacionamentos) adapter).setOnItemClickListener(new AdapterRelacionamentos.ControladorCardRelacionamentos() {
            @Override
            public void onItemClick(int position, View v) {
                // o que farei com o que for clicado ...
                Log.v("Relacionamentos", "Checado " + position);
                pos = position;
            }
        });
    }


    private void verificaCheckBox(){

    }

    public void onCheckBoxClicked(View v){
        CheckBox cb = (CheckBox) v;

        listaIds.get(0);

        if(listaIds.isEmpty()){
            Log.v("VOID", "Puramente Vazio");
        }else{
            for(Integer e: listaIds){
                Log.v("HAHA", ">>> " + e);
            }
        }

        if(cb.getId() == R.id.cbRelacionamento){
            Log.v("CheckBox", "Cliquei em alguém");
        }
    }



}
