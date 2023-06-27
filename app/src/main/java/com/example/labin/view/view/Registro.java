package com.example.labin.view.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.labin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    private EditText email, senha, senhaConfirmado;
    private Button cadastrar, sair;
    private FirebaseAuth auth;
    private CheckBox checkBox;
    private ProgressBar pbRegistro;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.edtEmailRegistro);
        senha = findViewById(R.id.edtSenhaRegistro);
        senhaConfirmado = findViewById(R.id.edtSenhaConfirmacaoRegistro);
        cadastrar = findViewById(R.id.btnCadastrar);
        sair = findViewById(R.id.btnSairRegistro);
        checkBox = findViewById(R.id.cbMostrarSenhaRegistro);
        pbRegistro = findViewById(R.id.pbRegistro);


        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaPrincipal();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    senhaConfirmado.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    senhaConfirmado.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String registrarEmail = email.getText().toString();
                String registrarSenha = senha.getText().toString();
                String senhaConfirmada = senhaConfirmado.getText().toString();

                if(!TextUtils.isEmpty(registrarEmail) || !TextUtils.isEmpty(registrarSenha) || !TextUtils.isEmpty(senhaConfirmada) ){
                    if(registrarSenha.equals(senhaConfirmada)){
                        pbRegistro.setVisibility(View.VISIBLE);
                        auth.createUserWithEmailAndPassword(registrarEmail, senhaConfirmada).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    if(mediaPlayer!=null){
                                        mediaPlayer.release();
                                    }

                                    mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.success);

                                    if(mediaPlayer!=null){
                                        mediaPlayer.start();
                                        mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release());
                                    }
                                    abrirTelaPrincipal();
                                }else{
                                    if(mediaPlayer!=null){
                                        mediaPlayer.release();
                                    }

                                    String error = task.getException().getMessage();
                                    Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                                    mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.error);

                                    if(mediaPlayer!=null){
                                        mediaPlayer.start();
                                        mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release());
                                    }
                                }
                                pbRegistro.setVisibility(View.INVISIBLE);
                            }
                        });
                    }else{
                        if(mediaPlayer!=null){
                            mediaPlayer.release();
                        }

                        Toast.makeText(getApplicationContext(), getString(R.string.mesma_senha), Toast.LENGTH_SHORT).show();
                        mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.error);

                        if(mediaPlayer!=null){
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release());
                        }
                    }
                }
            }
        });
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}