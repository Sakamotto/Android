package com.testes.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final ImageView ivAdicionar = (ImageView) findViewById(R.id.ivAddContato);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Button btnAddContato = (Button) findViewById(R.id.btCriarContato);
        Button btnListarContato = (Button) findViewById(R.id.btListarContato);


        btnAddContato.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent actAddContato = new Intent(MainActivity.this, CadastroContatos.class);
                startActivity(actAddContato);
            }
        });

        btnListarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actListarContato = new Intent(MainActivity.this, ListarContatos.class);
                startActivity(actListarContato);
            }
        });

//        ivAdicionar.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                ivAdicionar.startAnimation(anim);
//            }
//        });
    }


}
