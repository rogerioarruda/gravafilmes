package com.example.roger.exercicio4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActivityListaFilmes extends AppCompatActivity {

    RecyclerView lista;
    ArrayList<Filme> filmes;
    Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        lista = (RecyclerView) findViewById(R.id.recyclerViewFilmes);

        filmes = (ArrayList<Filme>) getIntent().getExtras().getSerializable("lista");

        // utlizando o adaptador linear
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(mLayoutManager);

        // usando o adaptador
        final Adaptador adaptador = new Adaptador(filmes);
        lista.setAdapter(adaptador);

        buttonSalvar = (Button) findViewById(R.id.btnSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle retorno = new Bundle();
                filmes = adaptador.getLista();
                retorno.putSerializable("lista", filmes);
                intent.putExtras(retorno);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
