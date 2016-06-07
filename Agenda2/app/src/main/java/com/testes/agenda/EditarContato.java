package com.testes.agenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by cristian on 07/06/16.
 */
public class EditarContato extends AppCompatActivity {

    EditText etNome;
    EditText etEmail;
    EditText etTelefone;
    ArrayList<String> dados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contato);
        dados = getIntent().getStringArrayListExtra("contato");

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTelefone = (EditText) findViewById(R.id.etTelefone);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
