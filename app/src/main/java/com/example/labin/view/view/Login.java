package com.example.labin.view.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private Button btnLogin, btnRegistrar;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        progressBar = findViewById(R.id.pbLogin);
        checkBox = findViewById(R.id.cbMostrarSenha);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCadastro();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginEmail = email.getText().toString();
                String loginSenha = senha.getText().toString();

                if(!TextUtils.isEmpty(loginSenha) || !TextUtils.isEmpty(loginEmail)){
                    progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(loginEmail, loginSenha).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        abrirTelaPrincipal();
                                    }else{
                                        String error = task.getException().getMessage();
                                        Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                }
            }
        });

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