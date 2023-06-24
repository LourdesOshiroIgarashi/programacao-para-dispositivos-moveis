package com.example.labin.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.labin.R;

public class Aluno extends AppCompatActivity {

    private Button addAluno, viewAluno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        //conectando os botões com o código Java
        addAluno = findViewById(R.id.addAluno);
        viewAluno = findViewById(R.id.viewAluno);


    }


    public void adicionarAluno(View view){
        Intent intent = new Intent(getApplicationContext(), AdicionarAluno.class);
        startActivity(intent);
    }

    public void visualizarAluno(View view){
        Intent intent = new Intent(getApplicationContext(), viewLabsAlunos.class);
        startActivity(intent);
    }
}