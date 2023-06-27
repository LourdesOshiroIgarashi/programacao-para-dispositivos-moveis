package com.example.labin.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
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


    private EditText latitudeCadastro, longitudeCadastro, nomeCadastro, FaculdadeResponsavel;
    private Button btnSalvarLab, btnAddFoto;
    private FirebaseDatabase database;
    private MediaPlayer mediaPlayer;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_laboratorio);

        latitudeCadastro =findViewById(R.id.latitudeCadastro);
        longitudeCadastro=findViewById(R.id.longitudeCadastro);
        nomeCadastro=findViewById(R.id.nomeCadastro);
        FaculdadeResponsavel=findViewById(R.id.FaculdadeResponsavel);
        btnSalvarLab=findViewById(R.id.btnSalvarLab);
        btnAddFoto=findViewById(R.id.btnAddFoto);


        btnAddFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void SalvarDados(View view){

        Laboratorio labs = new Laboratorio();





        if(verificaTexto(latitudeCadastro.getText().toString(), longitudeCadastro.getText().toString(), FaculdadeResponsavel.getText().toString(), nomeCadastro.getText().toString())){
            String entrada = latitudeCadastro.getText().toString();
            double valorLatitude = Double.parseDouble(entrada);
            labs.setLatitudeCadastro(valorLatitude);
            entrada = longitudeCadastro.getText().toString();
            double valorLongitude = Double.parseDouble(entrada);
            labs.setLongitudeCadastro(valorLongitude);
            labs.setFaculdadeResponsavel(FaculdadeResponsavel.getText().toString());
            labs.setNomeCadastro(nomeCadastro.getText().toString());

            database = FirebaseDatabase.getInstance();
            reference = database.getReference();

            if(mediaPlayer!=null){
                mediaPlayer.release();
            }

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Laboratorio");
            myRef.push().setValue(labs);
            mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.success);
            Toast.makeText(getApplicationContext(), getString(R.string.dados_salvos), Toast.LENGTH_LONG).show();
            if(mediaPlayer!=null){
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release());
            }
            finish();
        }

    }

    private boolean verificaTexto(String toString, String toString1, String toString2, String toString3) {


        if (toString == null || toString.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite uma latitude", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (toString1 == null || toString1.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite uma longitude", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (toString2 == null || toString2.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite uma faculdade", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (toString3 == null || toString3.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite o nome do laboratório", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}