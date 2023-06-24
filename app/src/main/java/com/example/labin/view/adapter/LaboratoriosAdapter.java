package com.example.labin.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labin.R;
import com.example.labin.view.entities.Laboratorio;

import java.util.List;

public class LaboratoriosAdapter extends RecyclerView.Adapter<LaboratoriosAdapter.MyViewHolder> {


    private List<Laboratorio> labs;
    private Context context;
    public LaboratoriosAdapter(List<Laboratorio> lista, Context c) {
        this.labs = lista;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_labs, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Laboratorio laboratorio = labs.get(position);

        holder.nomeLaboratorio.setText(laboratorio.getNomeCadastro());
        holder.faculdadeResponsavel.setText(laboratorio.getFaculdadeResponsavel());
        double dados = laboratorio.getLatitudeCadastro();
        String convert = Double.toString(dados);
        holder.latitude.setText(convert);
        dados = laboratorio.getLongitudeCadastro();
        convert = Double.toString(dados);
        holder.longitude.setText(convert);

        //foto padrão
        //holder.foto.setImageResource(R.drawable.baseline_logout_24);

    }

    @Override
    public int getItemCount() {
        return labs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView foto;
        TextView latitude, longitude, nomeLaboratorio, faculdadeResponsavel;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //foto = itemView.findViewById(R.id.fotoLab);
            latitude = itemView.findViewById(R.id.latLab);
            longitude = itemView.findViewById(R.id.longLab);
            nomeLaboratorio = itemView.findViewById(R.id.nomeLab);
            faculdadeResponsavel = itemView.findViewById(R.id.facLab);

        }
    }
}
