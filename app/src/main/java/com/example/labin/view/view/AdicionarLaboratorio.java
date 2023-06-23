package com.example.labin.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labin.R;
import com.example.labin.view.entities.Laboratorio;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdicionarLaboratorio extends AppCompatActivity {


    private EditText latitudeCadastro, longitudeCadastro, nomeCadastro, cursoCadastro, prodessorResponsavelCadastro;
    private Button btnSalvarLab;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_laboratorio);

        latitudeCadastro =findViewById(R.id.latitudeCadastro);
        longitudeCadastro=findViewById(R.id.longitudeCadastro);
        nomeCadastro=findViewById(R.id.nomeCadastro);
        cursoCadastro=findViewById(R.id.cursoCadastro);
        prodessorResponsavelCadastro =findViewById(R.id.prodessorResponsavelCadastro);
        prodessorResponsavelCadastro=findViewById(R.id.prodessorResponsavelCadastro);
        btnSalvarLab=findViewById(R.id.btnSalvarLab);


    }

    public void SalvarDados(View view){

        Laboratorio labs = new Laboratorio();
        labs.setCursoCadastro(cursoCadastro.getText().toString());
        String entrada = latitudeCadastro.getText().toString();
        double valorLatitude = Double.parseDouble(entrada);
        labs.setLatitudeCadastro(valorLatitude);
        entrada = longitudeCadastro.getText().toString();
        double valorLongitude = Double.parseDouble(entrada);
        labs.setLongitudeCadastro(valorLongitude);
        labs.setProfessorResponsavelCadastro(cursoCadastro.getText().toString());
        labs.setNomeCadastro(nomeCadastro.getText().toString());

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Laboratorio");
        myRef.push().setValue(labs);
        Toast.makeText(getApplicationContext(), "Dados salvos", Toast.LENGTH_LONG).show();
        finish();
    }
}