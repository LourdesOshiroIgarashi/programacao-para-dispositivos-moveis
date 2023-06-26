package com.example.labin.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labin.R;
import com.example.labin.view.entities.Aluno;
import com.example.labin.view.entities.Pesquisador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastroPesquisador extends AppCompatActivity {


    private Button button;
    private EditText LabsPesquisador, nomePesquisador, faculdadePesquisador;
    private DatabaseReference reference;

    private FirebaseDatabase database;
    //private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pesquisador);

        button = findViewById(R.id.button);
        LabsPesquisador = findViewById(R.id.LabsPesquisador);
        nomePesquisador = findViewById(R.id.nomePesquisador);
        faculdadePesquisador = findViewById(R.id.faculdadePesquisador);


        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Pesquisador");




    }

    public void Salvar(View view){
        Pesquisador pesquisador = new Pesquisador();
        pesquisador.setNome(nomePesquisador.getText().toString());
        pesquisador.setFaculdade(faculdadePesquisador.getText().toString());
        pesquisador.setLaboratorio(LabsPesquisador.getText().toString());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();
        pesquisador.setEmail(uid);


        database = FirebaseDatabase.getInstance();
        //reference = database.getReference();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Pesquisador");
        myRef.child(uid).setValue(pesquisador);
        Toast.makeText(getApplicationContext(), "Dados salvos", Toast.LENGTH_LONG).show();
        finish();
    }
}