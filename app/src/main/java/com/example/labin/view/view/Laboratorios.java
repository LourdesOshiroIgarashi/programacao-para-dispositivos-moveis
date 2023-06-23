package com.example.labin.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.labin.R;

public class Laboratorios extends AppCompatActivity {

    private Button addlaboratorio, viewLaboratorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorios);

        //conexão dos botões com o código java
        addlaboratorio = findViewById(R.id.addlaboratorio);
        viewLaboratorio = findViewById(R.id.viewLaboratorio);
    }



    public void adicionarLaboratorio(View view){
        Intent intent = new Intent(getApplicationContext(), AdicionarLaboratorio.class);
        startActivity(intent);
    }

    public void visualizarLaboratorio(View view){
        Intent intent = new Intent(getApplicationContext(), VisualizarLaboratorio.class);
        startActivity(intent);
    }
}