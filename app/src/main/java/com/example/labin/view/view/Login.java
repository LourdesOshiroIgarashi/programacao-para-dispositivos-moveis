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

public class Login extends AppCompatActivity {

    private EditText email, senha;
    private Button btnLogin, btnRegistrar, visitante;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        visitante = findViewById(R.id.visitante);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        progressBar = findViewById(R.id.pbLogin);
        checkBox = findViewById(R.id.cbMostrarSenha);


        visitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), viewLabsAlunos.class);
                startActivity(i);
                finish();
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastro();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = email.getText().toString();
                String loginSenha = senha.getText().toString();

                if(verificarTexto(loginEmail, loginSenha)){
                    if (!TextUtils.isEmpty(loginSenha) || !TextUtils.isEmpty(loginEmail)) {
                        progressBar.setVisibility(View.VISIBLE);
                        auth.signInWithEmailAndPassword(loginEmail, loginSenha).
                                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            if (mediaPlayer != null) {
                                                mediaPlayer.release();
                                            }
                                            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.success);
                                            if (mediaPlayer != null) {
                                                mediaPlayer.start();
                                                mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release());
                                            }
                                            abrirTelaPrincipal();
                                        } else {
                                            if (mediaPlayer != null) {
                                                mediaPlayer.release();
                                            }
                                            String error = task.getException().getMessage();
                                            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.error);
                                            Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_SHORT).show();
                                            if (mediaPlayer != null) {
                                                mediaPlayer.start();
                                                mediaPlayer.setOnCompletionListener(mp -> mediaPlayer.release());
                                            }
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    }
                                });
                    }
                }



            }
        });

    }

    private boolean verificarTexto(String loginEmail, String loginSenha) {

        //verificar login e senha se foram digitados
        if (loginEmail == null || loginEmail.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite um email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (loginSenha == null || loginSenha.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite uma senha", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void abrirCadastro() {
        Intent intent = new Intent(getApplicationContext(), Registro.class);
        startActivity(intent);
        finish();
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}