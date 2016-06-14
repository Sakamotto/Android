package com.testes.agenda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.testes.agenda.controle.Controller;
import com.testes.agenda.entidade.Evento;

import java.io.File;

/**
 * Created by cristian on 13/06/16.
 */
public class EditarEvento extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btnCancelar;
    Button btnSalvar;
    Button btnDeletar;

    EditText etTitulo;
    EditText etLocal;
    EditText etEndereco;
    EditText etData;
    EditText etMaxParticipantes;
    EditText etValor;
    Spinner spTipoEvento;
    Spinner spExclusividade;
    int position;
    int posExclusividade;
    int posTipo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);

        btnCancelar = (Button) findViewById(R.id.btCancelarEvento);
        btnSalvar = (Button) findViewById(R.id.btSalvarEvento);
        btnDeletar = (Button) findViewById(R.id.btDeletarEvento);

        etTitulo = (EditText) findViewById(R.id.etTituloEvento);
        etLocal = (EditText) findViewById(R.id.etLocal);
        etEndereco = (EditText) findViewById(R.id.etEndereco);
        etData = (EditText) findViewById(R.id.etDataEvento);
        etMaxParticipantes = (EditText) findViewById(R.id.etMaxParticipantes);
        etValor = (EditText) findViewById(R.id.etValor);
        spTipoEvento = (Spinner) findViewById(R.id.spGrupo);
        spExclusividade = (Spinner) findViewById(R.id.spExclusividade);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Evento e = new Evento();
                e.setTitulo(etTitulo.getText().toString());
                e.setLocal(etLocal.getText().toString());
                e.setEndereco(etEndereco.getText().toString());
                e.setData(etData.getText().toString());
                e.setValor(Float.parseFloat(etValor.getText().toString()));
                e.setMaxParticipantes(Integer.parseInt(etMaxParticipantes.getText().toString()));
                e.setTipoEvento(spTipoEvento.getSelectedItemPosition());
                e.setExclusividade(spExclusividade.getSelectedItemPosition());
                Controller.editar(new File(getFilesDir(), ""), Controller.EVENTO, position, e);
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.deletar(new File(getFilesDir(), ""), Controller.EVENTO, position);
                finish();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Evento e = (Evento) getIntent().getSerializableExtra("evento");

        posExclusividade = e.getExclusividade();
        posTipo = e.getTipoEvento();

        setAdapter();
        preencheCampos();
        cancelar();
    }


    private void setAdapter(){
        spTipoEvento = (Spinner) findViewById(R.id.spGrupo);
        spExclusividade = (Spinner) findViewById(R.id.spExclusividade);

        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.tipo_evento));
        ArrayAdapter<String> adapterExclusividade = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.exclusividade));

        spTipoEvento.setAdapter(adapterTipo);
        spExclusividade.setAdapter(adapterExclusividade);

        spTipoEvento.setSelection(posTipo);
        spExclusividade.setSelection(posExclusividade);

        spTipoEvento.setOnItemSelectedListener(this);
        spExclusividade.setOnItemSelectedListener(this);
    }



    private void cancelar(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void preencheCampos(){
        Evento evento = (Evento) getIntent().getSerializableExtra("evento");

        position = getIntent().getIntExtra("indiceEvento", 0);
        etTitulo.setText(evento.getTitulo());
        etLocal.setText(evento.getLocal());
        etEndereco.setText(evento.getEndereco());
        etData.setText(evento.getData());
        etMaxParticipantes.setText(Integer.toString(evento.getMaxParticipantes()));
        etValor.setText(Float.toString(evento.getValor()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
