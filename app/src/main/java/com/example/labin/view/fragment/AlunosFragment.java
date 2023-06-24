package com.example.labin.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.labin.R;
import com.example.labin.view.adapter.AlunosAdapter;
import com.example.labin.view.entities.Aluno;
import com.example.labin.view.entities.Laboratorio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlunosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlunosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerViewAlunos;
    private List<Aluno> listaAluno = new ArrayList<>();
    private AlunosAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public AlunosFragment() {
        // Required empty public constructor
    }

    public static AlunosFragment newInstance(String param1, String param2) {
        AlunosFragment fragment = new AlunosFragment();
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
        View view =  inflater.inflate(R.layout.fragment_alunos, container, false);

        recyclerViewAlunos = view.findViewById(R.id.recyclerListaAlunos);

        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Aluno");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Aluno aluno = snapshot.getValue(Aluno.class);
                    //String dado = snapshot.getValue(String.class);
                    listaAluno.add(aluno);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //adapter
        adapter = new AlunosAdapter(listaAluno, getActivity());

        //recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewAlunos.setLayoutManager(layoutManager);
        recyclerViewAlunos.setHasFixedSize(true);
        recyclerViewAlunos.setAdapter(adapter);

        return view;
    }
}