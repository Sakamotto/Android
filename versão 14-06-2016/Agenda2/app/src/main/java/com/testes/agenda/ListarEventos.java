package com.testes.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.testes.agenda.controle.Controller;
import com.testes.agenda.entidade.Evento;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 13/06/16.
 */
public class ListarEventos extends AppCompatActivity {

    private RecyclerView rvListar;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lmManager;
    Object objEventos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);
        rvListar = (RecyclerView) findViewById(R.id.rv_listar_eventos);

        assert rvListar != null;
        rvListar.setHasFixedSize(true);

        // usar um gerenciador de layout
        lmManager = new LinearLayoutManager(this);
        rvListar.setLayoutManager(lmManager);
        rvListar.setItemAnimator(new DefaultItemAnimator());
        objEventos = Controller.ler(new File(getFilesDir(), ""), Controller.EVENTO);

        adapter = new AdapterEventos(this, objEventos);
        rvListar.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        ((AdapterEventos) adapter).setOnItemClickListener(new AdapterEventos.ControladorCardEvento(){
            @Override
            public void onItemClick(int position, View v) {
                List<Evento> listaEventos = ((ArrayList<Evento>)objEventos);
                Evento eventoEditado = listaEventos.get(position);
                Intent editarEvento = new Intent(getApplicationContext(), EditarEvento.class);
                editarEvento.putExtra("evento", eventoEditado);
                editarEvento.putExtra("indiceEvento", position);
                startActivity(editarEvento);
            }
        });


    }
}
