package com.example.labin.view.view;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.labin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.labin.databinding.ActivityLocalizacaoLaboratoriosBinding;

public class LocalizacaoLaboratorios extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityLocalizacaoLaboratoriosBinding binding;
    double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLocalizacaoLaboratoriosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();

        String latS = intent.getStringExtra("lat");
        String lonS = intent.getStringExtra("lon");


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lab = new LatLng(122, 122);
        mMap.addMarker(new MarkerOptions().position(lab).title("Laboratório"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lab));
    }
}