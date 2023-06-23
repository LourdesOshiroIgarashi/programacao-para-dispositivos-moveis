package com.example.labin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.labin.R;
import com.example.labin.view.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                irLogin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentuser == null){
            irLogin();
            finish();
        }
    }

    public void irLogin(){
        Intent intent = new  Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
}