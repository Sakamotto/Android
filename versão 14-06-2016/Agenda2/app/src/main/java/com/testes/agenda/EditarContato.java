package com.testes.agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.testes.agenda.controle.Controller;
import com.testes.agenda.entidade.Amigo;
import com.testes.agenda.entidade.Colega;
import com.testes.agenda.entidade.Contato;
import com.testes.agenda.entidade.Familia;
import com.testes.agenda.entidade.GrupoContato;
import com.testes.agenda.entidade.Relacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 07/06/16.
 */
public class EditarContato extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText etNome;
    EditText etNascimento;
    EditText etEmail;
    EditText etTelefone;
    EditText etIdade;
    EditText etData;
    EditText etReferencia;
    EditText etProfissao;
    EditText etParentesco;
    Button btnSalvar;
    Button btnCancelar;
    Button btnDeletar;
    Spinner spGenero;
    Spinner spProximidade;
    List<Relacao> relacoes = new ArrayList<>();
    int position;
    int posGenero;
    int posProximidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contato);
        btnSalvar = (Button) findViewById(R.id.btSalvarContato);
        btnCancelar = (Button) findViewById(R.id.btCancelar);
        btnDeletar = (Button) findViewById(R.id.btDeletar);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etData = (EditText) findViewById(R.id.etData);
        etNascimento = (EditText) findViewById(R.id.etData);
        spGenero = (Spinner) findViewById(R.id.spGenero);
        spProximidade = (Spinner) findViewById(R.id.spProximidade);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato c = new Contato();
                c.setNome(etNome.getText().toString());
                c.setEmail(etEmail.getText().toString());
                c.setIdade(Integer.parseInt(etIdade.getText().toString()));
                c.setDataNascimento(etData.getText().toString());
                c.setCelular(etTelefone.getText().toString());
                c.setGenero(spGenero.getSelectedItemPosition());
                c.setNivelProximidade(spProximidade.getSelectedItemPosition());

                Controller.editar(new File(getFilesDir(), ""), Controller.CONTATO, position, c);


                finish();
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.deletar(new File(getFilesDir(), ""), Controller.CONTATO, position);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        posGenero = getIntent().getIntExtra("genero", 0);
        posProximidade = getIntent().getIntExtra("proximidade", 0);

        setAdapter();
        preencheCampos();
        cancelar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void preencheCampos(){
        etNome.setText(getIntent().getStringExtra("nome"));
        etEmail.setText(getIntent().getStringExtra("email"));
        etTelefone.setText(getIntent().getStringExtra("telefone"));
        int idade = getIntent().getIntExtra("idade", 0);
        etIdade.setText(Integer.toString(idade));
        etData.setText(getIntent().getStringExtra("nascimento"));
        position = getIntent().getIntExtra("indiceContato", 0);
        Serializable relacao = getIntent().getSerializableExtra("grupoContato");

        if(relacao != null){
            relacoes = ((GrupoContato) relacao).getList();
        }

        if(relacoes.isEmpty()){
            Log.v("Editar Contato", "A lista de realações está vazia");
        }else{
            verificaRelacoes();
        }
    }

    public void verificaRelacoes(){
        for(Relacao r: relacoes){
            if(r instanceof Amigo){
                LinearLayout llReferencia = (LinearLayout) findViewById(R.id.llReferencia);
                etReferencia = (EditText) findViewById(R.id.etReferencia);
                String referencia = ((Amigo) relacoes.get(0)).getReferencia();
                assert llReferencia != null;
                llReferencia.setVisibility(View.VISIBLE);
                etReferencia.setText(referencia);
                Log.v("Editar Contato", "Relação " + referencia);
            }else if(r instanceof Colega){
                LinearLayout llProfissao = (LinearLayout) findViewById(R.id.llProfissao);
                etProfissao = (EditText) findViewById(R.id.etProfissao);
                String profissao = ((Colega) r).getProfissao();
                assert llProfissao != null;
                llProfissao.setVisibility(View.VISIBLE);
                etReferencia.setText(profissao);
                Log.v("Editar Contato", "Relação " + profissao);

            }else if(r instanceof Familia){
                LinearLayout llParentesco = (LinearLayout) findViewById(R.id.llParentesco);
                etParentesco = (EditText) findViewById(R.id.etParentesco);
                String parentesco = ((Familia) r).getTipoParentesco();
                assert llParentesco != null;
                llParentesco.setVisibility(View.VISIBLE);
                etParentesco.setText(parentesco);
                Log.v("Editar Contato", "Relação " + parentesco);
            }
        }

    }

    private void setAdapter(){
        spGenero = (Spinner) findViewById(R.id.spGenero);
        spProximidade = (Spinner) findViewById(R.id.spProximidade);

        ArrayAdapter<String> adapterGenero = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.genero));
        ArrayAdapter<String> adapterProximidade = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.proximidade));

        spGenero.setAdapter(adapterGenero);
        spProximidade.setAdapter(adapterProximidade);

        spGenero.setSelection(posGenero);
        spProximidade.setSelection(posProximidade);

        spGenero.setOnItemSelectedListener(this);
        spProximidade.setOnItemSelectedListener(this);
    }



    public void cancelar(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
