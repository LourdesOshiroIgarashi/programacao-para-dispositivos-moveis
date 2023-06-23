package com.example.labin.view.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.labin.view.entities.Aluno;

import java.util.HashMap;
import java.util.Map;

public class AdicionarAluno extends AppCompatActivity {

    private EditText nomeAluno, laboratorioAluno, emailAluno, cursoAluno;
    private TextView txt1;
    private Button btnSalvarAluno;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_aluno);

        nomeAluno  = findViewById(R.id.nomeAluno);
        laboratorioAluno = findViewById(R.id.laboratorioAluno);
        emailAluno = findViewById(R.id.emailAluno);
        cursoAluno = findViewById(R.id.cursoAluno);
        btnSalvarAluno = findViewById(R.id.btnSalvarAluno);
       // txt1 = findViewById(R.id.txt1);


    }

    public void SalvarDados(View view){

        Aluno aluno1 = new Aluno();
        aluno1.setNome(nomeAluno.getText().toString());
        aluno1.setCurso(cursoAluno.getText().toString());
        aluno1.setEmail(emailAluno.getText().toString());
        aluno1.setLaboratorio(laboratorioAluno.getText().toString());



        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Aluno");
        myRef.push().setValue(aluno1);
        Toast.makeText(getApplicationContext(), "Dados salvos", Toast.LENGTH_LONG).show();
        finish();
    }
}