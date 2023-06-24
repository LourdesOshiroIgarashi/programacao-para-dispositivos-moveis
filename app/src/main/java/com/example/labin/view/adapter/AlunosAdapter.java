package com.example.labin.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labin.R;
import com.example.labin.view.entities.Aluno;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class AlunosAdapter extends RecyclerView.Adapter<AlunosAdapter.MyViewHolder> {

    private List<Aluno> aluno;
    private Context context;
    private DatabaseReference databaseReference;
    public AlunosAdapter(List<Aluno> lista, Context c) {
        this.aluno = lista;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_alunos, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Aluno aluno1 = aluno.get(position);

        holder.nome.setText(aluno1.getNome());
        holder.email.setText(aluno1.getEmail());
        holder.laboratorio.setText(aluno1.getLaboratorio());
        holder.curso.setText(aluno1.getCurso());

    }

    @Override
    public int getItemCount() {
        return aluno.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView curso, email, laboratorio,  nome;

        public MyViewHolder(View itemView){
            super(itemView);

            curso = itemView.findViewById(R.id.facLab);
            email = itemView.findViewById(R.id.latLab);
            laboratorio = itemView.findViewById(R.id.longLab);
            nome = itemView.findViewById(R.id.nomeLab);

        }
    }

    public void recuperarAlunos(){

    }

}
