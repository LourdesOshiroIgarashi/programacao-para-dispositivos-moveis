package com.example.labin.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.labin.R;

public class AdicionarAluno extends AppCompatActivity {

    private EditText nomeAluno, laboratorioAluno, emailAluno, cursoAluno;
    private Button btnSalvarAluno;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_aluno);

        nomeAluno  = findViewById(R.id.nomeAluno);
        laboratorioAluno = findViewById(R.id.laboratorioAluno);
        emailAluno = findViewById(R.id.emailAluno);
        cursoAluno = findViewById(R.id.cursoAluno);
        btnSalvarAluno = findViewById(R.id.btnSalvarAluno);



    }
}