package com.testes.agenda;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.testes.agenda.entidade.Contato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamConstants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 01/06/16.
 */
public class CadastroContatos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public static String date;
    public static int TAM_PROXIMIDADE = 5;
    public static String DIR_DAT = "teste.dat";
    private int proximidade = 0;
    private String genero = "";
    EditText etData;
    EditText etIdade;
    List<Contato> listaObjContato = new ArrayList<>();

    EditText etReferencia;
    EditText etParentesco;
    EditText etProfissao;
    EditText etNome;
    EditText etEmail;
    EditText etTelefone;

    LinearLayout llReferencia;
    LinearLayout llParentesco;
    LinearLayout llProfissao;

    TextView tvReferencia;
    TextView tvParentesco;
    TextView tvProfissao;

//    Spinner spGenero;
//    Spinner spProximidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contatos);

        Spinner spGenero = (Spinner) findViewById(R.id.spGenero);
        Spinner spProximidade = (Spinner) findViewById(R.id.spProximidade);
        Button btnSalvar = (Button) findViewById(R.id.btSalvarContato);
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

        etData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.genero));
        ArrayAdapter<String> adapterProximidade = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.proximidade));

        assert spGenero != null;
        spGenero.setAdapter(adapter);
        assert spProximidade != null;
        spProximidade.setAdapter(adapterProximidade);

        spGenero.setOnItemSelectedListener(this);
        spProximidade.setOnItemSelectedListener(this);


        assert btnSalvar != null;
        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Contato a = inserirContato(new Contato());
                salvarContato(a, DIR_DAT);
                recuperarContato(DIR_DAT);
            }
        });
    }

    public void showDatePickerDialog(View v){
        DialogFragment df = new DataPickerFragment();
        df.show(getFragmentManager(), "dataPicker");
        etData.setText(date);
    }

    private void salvarContato(Contato c, String local){
        try{
            File arq = new File(getFilesDir() + File.separator + local);
            List<Contato> listaContatos = new ArrayList<>();

            if(arq.exists()){
                FileInputStream fLeitura = new FileInputStream(getFilesDir() + File.separator + local);
                ObjectInputStream objLeitura = new ObjectInputStream(fLeitura);
                listaContatos = (ArrayList<Contato>) objLeitura.readObject();
                objLeitura.close();
            }

            FileOutputStream arqGravar = new FileOutputStream(getFilesDir() + File.separator +local);
            ObjectOutputStream objGravar = new ObjectOutputStream(arqGravar);

            listaContatos.add(c);

            objGravar.writeObject(listaContatos);
            objGravar.flush();
            objGravar.close();

            arqGravar.flush();
            arqGravar.close();

            Log.v("Goku: ", "Salvo Com Sucesso! Uuul");
            Toast t = Toast.makeText(getApplicationContext(), "Contato salvo com sucesso!", Toast.LENGTH_LONG);
            t.show();
        }catch (Exception e){
            Log.e("Freeza", "Um erro ocorreu no I/O");
            e.printStackTrace();
        }
    }

    private void recuperarContato(String nomeArq){
        // Recuperando os Objetos salvos ...
        try{
            FileInputStream arqLeitura = new FileInputStream(getFilesDir() + File.separator + nomeArq);
            ObjectInputStream objLeitura = new ObjectInputStream(arqLeitura);
            List<Contato> listaContatos = (ArrayList<Contato>) objLeitura.readObject();

            if(listaContatos.isEmpty()){
                Log.v("Goku","Nenhum contato encontrado!");
            }else{
                // Imprime os contatos no console
                Log.v("Goku","*** Entrei Aqui xD ***");
                for (Contato c: listaContatos) {
                    Log.v("Goku","Entrei Aqui xD");
                    c.print();
                }
            }
            objLeitura.close();
            arqLeitura.close();

        }catch (Exception e){
            Log.e("Freeza", e.getMessage());
        }
    }

    public List<Contato> abreContato(String nomeArq){
        FileInputStream arqLeitura;
        ObjectInputStream objLeitura;
        // Recuperando os Objetos salvos ...
        try{
            arqLeitura = new FileInputStream(getFilesDir() + File.separator + nomeArq);
            objLeitura = new ObjectInputStream(arqLeitura);


            objLeitura.close();
            arqLeitura.close();

            return (List<Contato>) objLeitura.readObject();

        }catch (Exception e){
            Log.e("Freeza", "Abre Contato");
            Log.e("Freeza", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.v("Goku Spinner",parent.getItemAtPosition(position).toString() + " <--- KKKKKKK");
        if(parent.getId() == R.id.spGenero){
            setGenero(parent.getItemAtPosition(position).toString());
        }else if(parent.getId() == R.id.spProximidade){
            setProximidade(Integer.parseInt(parent.getItemAtPosition(position).toString()));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onCheckboxClicked(View view) {
        CheckBox cbAmigo = (CheckBox) findViewById(R.id.cbAmigo);
        CheckBox cbColega = (CheckBox) findViewById(R.id.cbColega);
        CheckBox cbFamilia = (CheckBox) findViewById(R.id.cbFamilia);

        if(cbAmigo.isChecked()){
            llReferencia.setVisibility(View.VISIBLE);
        }else if(!cbAmigo.isChecked()){
            llReferencia.setVisibility(View.GONE);
        }if(cbColega.isChecked()){
            llProfissao.setVisibility(View.VISIBLE);
        }else if(!cbColega.isChecked()){
            llProfissao.setVisibility(View.GONE);
        }if(cbFamilia.isChecked()){
            llParentesco.setVisibility(View.VISIBLE);
        }else if(!cbFamilia.isChecked()){
            llParentesco.setVisibility(View.GONE);
        }
    }

    private Contato inserirContato(Contato c){
        c.setCelular(etTelefone.getText().toString());
        c.setNome(etNome.getText().toString());
        c.setEmail(etEmail.getText().toString());
        c.setNivelProximidade(getProximidade());
        c.setGenero(getGenero().charAt(0));
        c.setIdade(Integer.parseInt(etIdade.getText().toString()));
        return c;
    }


    public int getProximidade() {
        return proximidade;
    }

    public void setProximidade(int proximidade) {
        this.proximidade = proximidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
