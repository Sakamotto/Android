package com.testes.agenda;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.testes.agenda.controle.Controller;
import com.testes.agenda.entidade.Contato;
import com.testes.agenda.entidade.GrupoContato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by cristian on 01/06/16.
 */
public class CadastroContatos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public static String date;
    private int proximidade = 0;
    private boolean amigo = false, colega = false, familia = false;
    private int genero;
    EditText etData;
    EditText etIdade;

    EditText etReferencia;
    EditText etParentesco;
    EditText etProfissao;
    EditText etNome;
    EditText etEmail;
    EditText etTelefone;
    DatePickerDialog.OnDateSetListener escutaData;
    Button btnRelacionamentos;
    private Calendar calendar;

    LinearLayout llReferencia;
    LinearLayout llParentesco;
    LinearLayout llProfissao;

    Spinner spGenero;
    Spinner spProximidade;

    TextView tvReferencia;
    TextView tvParentesco;
    TextView tvProfissao;

    int dia = Calendar.getInstance().get(Calendar.YEAR);
    int mes = Calendar.getInstance().get(Calendar.MONTH);
    int ano = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    public static int CONTATOS_RELACIONADOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contatos);
        calendar = Calendar.getInstance();
        spGenero = (Spinner) findViewById(R.id.spGenero);
        spProximidade = (Spinner) findViewById(R.id.spProximidade);
        Button btnSalvar = (Button) findViewById(R.id.btSalvarContato);
        btnRelacionamentos = (Button) findViewById(R.id.btAdicionarRelacionamento);
        etData = (EditText) findViewById(R.id.etData);
        etIdade = (EditText) findViewById(R.id.etIdade);

        tvReferencia = (TextView) findViewById(R.id.tvReferencia);
        tvParentesco = (TextView) findViewById(R.id.tvParentesco);
        tvProfissao  = (TextView) findViewById(R.id.tvProfissao);

        etReferencia = (EditText) findViewById(R.id.etReferencia);
        etParentesco = (EditText) findViewById(R.id.etParentesco);
        etProfissao = (EditText) findViewById(R.id.etProfissao);
        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTelefone = (EditText) findViewById(R.id.etTelefone);

        llReferencia = (LinearLayout) findViewById(R.id.llReferencia);
        llProfissao = (LinearLayout) findViewById(R.id.llProfissao);
        llParentesco = (LinearLayout) findViewById(R.id.llParentesco);

        assert btnSalvar != null;
        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                salvarContato(popularContato());
                finish();
            }
        });

        btnRelacionamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent relacionamentos = new Intent(CadastroContatos.this, CadastroRelacionamentos.class);
                startActivityForResult(relacionamentos, CONTATOS_RELACIONADOS);
            }
        });

        escutaData = listenerDate();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CONTATOS_RELACIONADOS){
            if(resultCode == RESULT_OK){
                // ...
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        changeDate();
        setAdapters();
    }

    private GrupoContato criarGrupo(){
        GrupoContato grupoContato = new GrupoContato();
        if(amigo){
            grupoContato.criarAmigo(this.etReferencia.getText().toString());
        }
        if(colega){
            grupoContato.criarColega(this.etProfissao.getText().toString());
        }
        if(familia){
            grupoContato.criarFamilia(this.etParentesco.getText().toString());
        }
        grupoContato.apply();
        return grupoContato;
    }

    private void salvarContato(Contato c){
        Controller.adicionar(new File(getFilesDir(), ""), Controller.CONTATO, c);
    }

    private void setAdapters(){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.genero));
        ArrayAdapter<String> adapterProximidade = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.proximidade));


        assert spGenero != null;
        spGenero.setAdapter(adapter);
        assert spProximidade != null;
        spProximidade.setAdapter(adapterProximidade);

        spGenero.setOnItemSelectedListener(this);
        spProximidade.setOnItemSelectedListener(this);

    }


    private DatePickerDialog.OnDateSetListener listenerDate(){
        return new DatePickerDialog.OnDateSetListener() {

            // Sempre que o usuário escolher adata e clicar em Ok, o método onDateSet é chamado e a data é inserida nas variáveis.
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate(dayOfMonth, monthOfYear+1, year);
                etData.setText(new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR")).format(calendar.getTime()));
                date = dayOfMonth + "/" +(monthOfYear+1) + "/" + year;
                Log.v("Data", date);
            }
        };
    }


    private void changeDate() {
        // Verifica se o foco está no EditText referente a data do evento.
        etData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Se o foco está no EditText da data, então mostra um diálogo para o usuário escolher a data.
                if(hasFocus){
                    new DatePickerDialog(CadastroContatos.this, escutaData, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        // Só o Listenner para o Focus não é o suficiente. Temos que verificar se o usuário deseja alterar a data novamente logo em seguida.
        // Para isso, usamos o setOnClickListener
        etData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                new DatePickerDialog(CadastroContatos.this, escutaData, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void updateDate(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.v("Goku Spinner",parent.getItemAtPosition(position).toString());
        if(parent.getId() == R.id.spGenero){
            setGenero(parent.getSelectedItemPosition());
        }else if(parent.getId() == R.id.spProximidade){
            setProximidade(parent.getSelectedItemPosition());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // ALGUMA MENSAGEM AQUI ...
    }

    public void onCheckboxClicked(View view) {
        CheckBox cbAmigo = (CheckBox) findViewById(R.id.cbAmigo);
        CheckBox cbColega = (CheckBox) findViewById(R.id.cbColega);
        CheckBox cbFamilia = (CheckBox) findViewById(R.id.cbFamilia);

        if(cbAmigo.isChecked()){
            amigo = true;
            llReferencia.setVisibility(View.VISIBLE);
        }else if(!cbAmigo.isChecked()){
            llReferencia.setVisibility(View.GONE);
        }if(cbColega.isChecked()){
            colega = true;
            llProfissao.setVisibility(View.VISIBLE);
        }else if(!cbColega.isChecked()){
            llProfissao.setVisibility(View.GONE);
        }if(cbFamilia.isChecked()){
            familia = true;
            llParentesco.setVisibility(View.VISIBLE);
        }else if(!cbFamilia.isChecked()){
            llParentesco.setVisibility(View.GONE);
        }
    }

    private Contato popularContato(){
        Contato c = new Contato();
        c.setCelular(etTelefone.getText().toString());
        c.setNome(etNome.getText().toString());
        c.setEmail(etEmail.getText().toString());
        c.setNivelProximidade(getProximidade());
        c.setGenero(getGenero());
        c.setDataNascimento(date);
        c.setIdade(Integer.parseInt(etIdade.getText().toString()));
        c.setGrupoContato(criarGrupo());
        return c;
    }


    public int getProximidade() {
        return proximidade;
    }

    public void setProximidade(int proximidade) {
        this.proximidade = proximidade;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
}
