package com.testes.agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

/**
 * Created by cristian on 07/06/16.
 */
public class ListarContatos extends AppCompatActivity {

    private RecyclerView rvListar;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lmManager;
    CadastroContatos cC = new CadastroContatos();

    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_listar_contatos);
        TextView titulo = (TextView) findViewById(R.id.titulo);
        rvListar = (RecyclerView) findViewById(R.id.rv_listar_contatos);
        rvListar.setHasFixedSize(true);
        // usar um gerenciador de layout
        lmManager = new LinearLayoutManager(this);
        rvListar.setLayoutManager(lmManager);

        adapter = new MyAdapter(cC.abreContato(CadastroContatos.DIR_DAT));
        rvListar.setAdapter(adapter);

    }
}
