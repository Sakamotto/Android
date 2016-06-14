package com.testes.agenda;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.testes.agenda.controle.Controller;
import com.testes.agenda.controle.EventosController;
import com.testes.agenda.entidade.Evento;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by cristian on 03/06/16.
 */
public class CadastroEventos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spTipo;
    Spinner spExclusividade;
    EditText etTitulo;
    EditText etLocal;
    EditText etEndereco;
    EditText etData;
    EditText etMaxParticipantes;
    EditText etValor;
    String date = "";

    // Componentes para Data ...
    DatePickerDialog.OnDateSetListener escutaData;
    private Calendar calendar;
    int dia = Calendar.getInstance().get(Calendar.YEAR);
    int mes = Calendar.getInstance().get(Calendar.MONTH);
    int ano = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    // fim
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        calendar = Calendar.getInstance();
        etTitulo = (EditText) findViewById(R.id.etTituloEvento);
        etLocal = (EditText) findViewById(R.id.etLocal);
        etEndereco = (EditText) findViewById(R.id.etEndereco);
        etData = (EditText) findViewById(R.id.etDataEvento);
        etMaxParticipantes = (EditText) findViewById(R.id.etMaxParticipantes);
        etValor = (EditText) findViewById(R.id.etValor);

        Button btnSalvar = (Button) findViewById(R.id.btSalvarEvento);

        escutaData = listenerDate();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEvento(popularEvento());
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setAdapters();
        changeDate();
    }

    private Evento popularEvento(){
        Evento e = new Evento();

        e.setTitulo(etTitulo.getText().toString());
        e.setLocal(etLocal.getText().toString());
        e.setEndereco(etEndereco.getText().toString());
        e.setData(etData.getText().toString());
        e.setValor(Float.parseFloat(etValor.getText().toString()));
        e.setMaxParticipantes(Integer.parseInt(etMaxParticipantes.getText().toString()));
        Log.v("MAX PARTC", etMaxParticipantes.getText().toString());
        e.setTipoEvento(spTipo.getSelectedItemPosition());
        e.setExclusividade(spExclusividade.getSelectedItemPosition());

        return e;
    }

    private void salvarEvento(Evento e){
        Controller.adicionar(new File(getFilesDir() + ""), Controller.EVENTO, e);
    }


    private void setAdapters(){
        spTipo = (Spinner) findViewById(R.id.spGrupo);
        spExclusividade = (Spinner) findViewById(R.id.spExclusividade);

        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.tipo_evento));
        ArrayAdapter<String> adapterExclusividade = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.exclusividade));

        spTipo.setAdapter(adapterTipo);
        spExclusividade.setAdapter(adapterExclusividade);

        spTipo.setOnItemSelectedListener(this);
        spExclusividade.setOnItemSelectedListener(this);

    }

    public void updateDate(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
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
                    new DatePickerDialog(CadastroEventos.this, escutaData, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        // Só o Listenner para o Focus não é o suficiente. Temos que verificar se o usuário deseja alterar a data novamente logo em seguida.
        // Para isso, usamos o setOnClickListener
        etData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                new DatePickerDialog(CadastroEventos.this, escutaData, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Colocar aqui o setTipo e setExclusividade ...
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
