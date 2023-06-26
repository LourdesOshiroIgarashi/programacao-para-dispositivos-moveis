package com.example.labin.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.labin.R;
import com.example.labin.view.adapter.LaboratoriosAdapter;
import com.example.labin.view.entities.Laboratorio;
import com.example.labin.view.view.LocalizacaoLaboratorios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LaboratoriosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LaboratoriosFragment extends Fragment {


    private RecyclerView  recyclerViewListaLabs;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LaboratoriosAdapter laboratoriosAdapter;
    private ArrayList<Laboratorio> listalabs = new ArrayList<>();

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public LaboratoriosFragment() {
        // Required empty public constructor
    }

    public static LaboratoriosFragment newInstance(String param1, String param2) {
        LaboratoriosFragment fragment = new LaboratoriosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_laboratorios, container, false);
        recyclerViewListaLabs = view.findViewById(R.id.recyclerViewListaLabs);

        //
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Laboratorio");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //listalabs = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Laboratorio laboratorio = snapshot.getValue(Laboratorio.class);
                    //String dado = snapshot.getValue(String.class);
                    listalabs.add(laboratorio);
                }

                laboratoriosAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Ocorreu um erro na leitura dos dados
            }
        });

            //

        //adapter
        laboratoriosAdapter = new LaboratoriosAdapter(listalabs, getActivity());

        //recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewListaLabs.setLayoutManager(layoutManager);
        recyclerViewListaLabs.setHasFixedSize(true);
        recyclerViewListaLabs.setAdapter(laboratoriosAdapter);


        laboratoriosAdapter.setOnItemClickListener(new LaboratoriosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                double lat, lon;
                lat = listalabs.get(position).getLatitudeCadastro();
                lon = listalabs.get(position).getLatitudeCadastro();
                //Intent intent = new Intent(getContext(), LocalizacaoLaboratorios.class);
                //startActivity(intent);
            }
        });

        return view;
    }

}