package com.testes.agenda;

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
public class EditarContato extends AppCompatActivity {

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
    List<Relacao> relacoes = new ArrayList<>();
    int position;

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

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato c = new Contato();
                c.setNome(etNome.getText().toString());
                c.setEmail(etEmail.getText().toString());
                c.setIdade(Integer.parseInt(etIdade.getText().toString()));
                c.setDataNascimento(etData.getText().toString());
                c.setCelular(etTelefone.getText().toString());
                editarContato(position, c);
                finish();
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarContato(position);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    public void cancelar(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void deletarContato(int position){

        try {
            File arquivo = new File(new File(getFilesDir(), "") + File.separator + "teste.dat");
            ArrayList<Contato> listaContatos = new ArrayList<>();

            if(arquivo.exists()){
                Log.v("Deletar Contato", "Tô entrando aqui ...");
                ObjectInputStream leitura = new ObjectInputStream(new FileInputStream(arquivo));
                listaContatos = (ArrayList<Contato>)leitura.readObject();
                leitura.close();
            }

            arquivo = new File(new File(getFilesDir(), "") + File.separator + "teste.dat");
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));
            listaContatos.remove(position);
            saida.writeObject(listaContatos);
            saida.close();

            Toast.makeText(getApplicationContext(), "Contato deletado com sucesso!", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.e("Freeza", "Erro ao deletar contato");
            e.printStackTrace();
        }
    }


    public void editarContato(int position, Contato c){

        try{
            File arquivo = new File(new File(getFilesDir(), "") + File.separator + CadastroContatos.DIR_DAT);
            ArrayList<Contato> listaContatos = new ArrayList<>();

            if(arquivo.exists()){
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arquivo));
                listaContatos = (ArrayList<Contato>) entrada.readObject();
                entrada.close();
            }

            arquivo = new File(new File(getFilesDir(), "") + File.separator + CadastroContatos.DIR_DAT);
            ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(arquivo));
            listaContatos.remove(position);
            listaContatos.add(position, c);
            saida.writeObject(listaContatos);
            saida.close();
            Log.v("Editar Contato", "CONTATO EDITADO COM SUCESSSO!");
            Toast.makeText(getApplicationContext(), "Contato editado com sucesso!", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.e("Freeza", "Erro ao editar contato");
            e.printStackTrace();
        }
    }

}
