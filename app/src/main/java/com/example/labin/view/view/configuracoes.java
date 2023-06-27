package com.example.labin.view.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labin.R;
import com.example.labin.databinding.ActivityMainBinding;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.Executor;

public class configuracoes extends AppCompatActivity {

    private Button addFoto, btntirarFoto, btnaddFoto;
    private ImageView imagemFoto;
    private TextView nomePesquisador, faculdadePesquisador, LaboratorioPesquisador;
    private FirebaseDatabase database;
    private static final int PERMISSION_REQUEST_CODE = 200;
    static final int GALLERY = 2;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    byte foto[];
    private static final String IMAGE_DIRECTORY = "/camera2022";
    private DatabaseReference reference;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        btnaddFoto = findViewById(R.id.btnaddFoto);
        imagemFoto = findViewById(R.id.imagemFoto);
        nomePesquisador = findViewById(R.id.nomePesquisador);
        faculdadePesquisador = findViewById(R.id.faculdadePesquisador);
        LaboratorioPesquisador = findViewById(R.id.LaboratorioPesquisador);
        btntirarFoto = findViewById(R.id.btntirarFoto);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btntirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Camera camera = Camera.open(1);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        binding.btnaddFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY);
            }
        });



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
        finish();git
    }
    @Override
    public void onResume() {
        super.onResume();
        if (checkPermission()) {
            // Success
        } else {
            requestPermission();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            binding.imagemFoto.setImageBitmap(resizeImage(bitmap,
                    300, 600));
            saveImage(bitmap);
            Toast.makeText(configuracoes.this, "Imagem Salva!",
                    Toast.LENGTH_SHORT).show();
        }else if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                            this.getContentResolver(),
                            contentURI);
                    // Salvando a imagem
                    String path = saveImage(bitmap);
                    Log.i("TAG","Path: " + path);
                    Toast.makeText(configuracoes.this, "Imagem OK!",
                            Toast.LENGTH_SHORT).show();
                    // Print on screen
                    binding.imagemFoto.setImageBitmap(resizeImage(bitmap, 300, 600));
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(configuracoes.this, "Falha!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public String saveImage(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(
                bitmap.getWidth() * bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        foto = bytes.toByteArray();

        File directory = new File(Environment.getExternalStorageDirectory()
                + IMAGE_DIRECTORY);
        // Criando o diretório caso ele não exista!
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            File fileImage = new File(directory,
                    Calendar.getInstance().getTimeInMillis() +".jpg");
            fileImage.createNewFile();
            FileOutputStream fo = new FileOutputStream(fileImage);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{fileImage.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved:->" + fileImage.getAbsolutePath());
            return fileImage.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public static Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {
        Matrix m = new Matrix();
        m.setScale((float) newWidth / bitmap.getWidth(), (float) newHeight / bitmap.getHeight());
        //m.postRotate(90, 150, 300);
        Bitmap output = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
    }
    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted",
                            Toast.LENGTH_SHORT).show();
                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                    binding.btnTirarFoto.setVisibility(View.INVISIBLE);
                    binding.btnCarregarFoto.setVisibility(View.INVISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(configuracoes.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}

