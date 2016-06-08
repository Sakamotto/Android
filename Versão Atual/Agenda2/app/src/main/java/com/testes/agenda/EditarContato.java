package com.testes.agenda;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.testes.agenda.entidade.Amigo;
import com.testes.agenda.entidade.Contato;
import com.testes.agenda.entidade.GrupoContato;
import com.testes.agenda.entidade.Relacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cristian on 07/06/16.
 */
public class EditarContato extends AppCompatActivity {

    EditText etNome;
    EditText etEmail;
    EditText etTelefone;
    EditText etIdade;
    EditText etData;
    EditText etReferencia;
    EditText etProfissao;
    EditText etParentesco;
    Button btnSalvar;
    Button btnCancelar;
    Spinner spGenero;
    List<Relacao> relacoes;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contato);
        btnSalvar = (Button) findViewById(R.id.btSalvarContato);
        btnCancelar = (Button) findViewById(R.id.btCancelar);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etData = (EditText) findViewById(R.id.etData);
        spGenero = (Spinner) findViewById(R.id.spGenero);
        preencheCampos();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastroContatos cadastro = new CadastroContatos();
                Contato c = new Contato();
                c.setNome(etNome.getText().toString());
                c.setEmail(etEmail.getText().toString());
                c.setIdade(Integer.parseInt(etIdade.getText().toString()));
                c.setDataNascimento(etData.getText().toString());

                editarContato(position, c);

                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
//        preencheCampos();
        cancelar();
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
        relacoes = ((GrupoContato) relacao).getList();

        if(relacoes.isEmpty()){
            Log.v("Editar Contato", "A lista de realações está vazia");
        }else{
            if(relacoes.get(0) != null){
                LinearLayout llReferencia = (LinearLayout) findViewById(R.id.llReferencia);
                etReferencia = (EditText) findViewById(R.id.etReferencia);
                String referencia = ((Amigo) relacoes.get(0)).getReferencia();
                llReferencia.setVisibility(View.VISIBLE);
                etReferencia.setText(referencia);
            }
            Log.v("Editar Contato", "Relação " + ((Amigo)relacoes.get(0)).getReferencia());
        }
    }

    public void cancelar(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void editarContato(int position, Contato c){
        try{
            File arq = new File(new File(getFilesDir(), "") + File.separator + CadastroContatos.DIR_DAT);
            List<Contato> listaContatos;

            if(arq.exists()){
                ObjectInputStream objLeitura = new ObjectInputStream(new FileInputStream(arq));
                listaContatos = (ArrayList<Contato>) objLeitura.readObject();

                listaContatos.get(position).setGenero(c.getGenero());
                listaContatos.get(position).setNome(c.getNome());
                listaContatos.get(position).setEmail(c.getEmail());

                objLeitura.close();
                Log.v("Goku: ", "Contato editado com sucesso! Uuul");
                Toast t = Toast.makeText(getApplicationContext(), "Contato editado com sucesso!", Toast.LENGTH_LONG);
                t.show();
            }else{
                Log.e("Goku: ", "Contato inexistente!");
            }
        }catch (Exception e){
            Log.e("Freeza", "Um erro ocorreu no I/O tentando editar o contato");
            e.printStackTrace();
        }
    }

}
