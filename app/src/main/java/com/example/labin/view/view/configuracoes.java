package com.example.labin.view.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labin.R;
import com.example.labin.view.entities.Pesquisador;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.Executor;

public class configuracoes extends AppCompatActivity {

    private Button addFoto;
    private ImageView imagemFoto;
    private TextView nomePesquisador, faculdadePesquisador, LaboratorioPesquisador;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        addFoto = findViewById(R.id.addFoto);
        imagemFoto = findViewById(R.id.imagemFoto);
        nomePesquisador = findViewById(R.id.nomePesquisador);
        faculdadePesquisador = findViewById(R.id.faculdadePesquisador);
        LaboratorioPesquisador = findViewById(R.id.LaboratorioPesquisador);






        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Pesquisador").child(uid);
        try {
            Toast.makeText(this, reference.toString(), Toast.LENGTH_LONG).show();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Pesquisador pesquisador = dataSnapshot.getValue(Pesquisador.class);
                    nomePesquisador.setText(pesquisador.getNome());
                    faculdadePesquisador.setText(pesquisador.getFaculdade());
                    LaboratorioPesquisador.setText(pesquisador.getLaboratorio());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }catch (Exception e){
            nomePesquisador.setText("");
            faculdadePesquisador.setText("");
            LaboratorioPesquisador.setText("");
        }



        addFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //configurar para imagem ser salva em memoria
                imagemFoto.setDrawingCacheEnabled(true);
                imagemFoto.buildDrawingCache();

                //recuperar bitmap da imagem
                Bitmap bitmap = imagemFoto.getDrawingCache();

                //comprimir o bitmap para um formato png/jpeg
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                byte[] dadosImagem = baos.toByteArray();



                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");

                //Nome da imagem aleatoria
                String nomeArquivo = UUID.randomUUID().toString();
                StorageReference imagemRef = imagens.child(nomeArquivo+".jpeg");

                UploadTask uploadTask = imagemRef.putBytes(dadosImagem);

                uploadTask.addOnFailureListener(configuracoes.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(configuracoes.this, getString(R.string.upload_imagem_falhou) +e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(configuracoes.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imagemRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(configuracoes.this, "Sucesso no upload da imagem: ", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });



            }
        });
    }

    public  void continuarCadastro(View view){
        Toast.makeText(configuracoes.this, "iniciando a atividade de cadastro", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, cadastroPesquisador.class);
        startActivity(i);
    }
}