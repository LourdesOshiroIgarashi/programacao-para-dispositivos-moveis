package com.example.labin.view.entities;

import android.widget.EditText;

public class Laboratorio {

    private String  nomeCadastro, cursoCadastro, professorResponsavelCadastro;
    private double latitudeCadastro, longitudeCadastro;

    public Laboratorio() {
    }

    public String getNomeCadastro() {
        return nomeCadastro;
    }

    public void setNomeCadastro(String nomeCadastro) {
        this.nomeCadastro = nomeCadastro;
    }

    public String getCursoCadastro() {
        return cursoCadastro;
    }

    public void setCursoCadastro(String cursoCadastro) {
        this.cursoCadastro = cursoCadastro;
    }

    public String getProdessorResponsavelCadastro() {
        return professorResponsavelCadastro;
    }

    public void setProfessorResponsavelCadastro(String prodessorResponsavelCadastro) {
        this.professorResponsavelCadastro = prodessorResponsavelCadastro;
    }

    public double getLatitudeCadastro() {
        return latitudeCadastro;
    }

    public void setLatitudeCadastro(double latitudeCadastro) {
        this.latitudeCadastro = latitudeCadastro;
    }

    public double getLongitudeCadastro() {
        return longitudeCadastro;
    }

    public void setLongitudeCadastro(double longitudeCadastro) {
        this.longitudeCadastro = longitudeCadastro;
    }
}
