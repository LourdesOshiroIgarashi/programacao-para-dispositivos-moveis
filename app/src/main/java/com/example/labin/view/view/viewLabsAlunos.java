package com.example.labin.view.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.labin.R;
import com.example.labin.view.fragment.AlunosFragment;
import com.example.labin.view.fragment.LaboratoriosFragment;

public class viewLabsAlunos extends AppCompatActivity {


    private Button btnLab, btnAluno;
    private AlunosFragment alunosFragment;
    private LaboratoriosFragment laboratoriosFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_laboratorio);

        btnLab = findViewById(R.id.btnLab);
        btnAluno = findViewById(R.id.btnAluno);







        btnAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alunosFragment = new AlunosFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, alunosFragment);
                transaction.commit();
            }
        });

        btnLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laboratoriosFragment = new LaboratoriosFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameConteudo, laboratoriosFragment);
                transaction.commit();
            }
        });
    }
}