package com.example.labin.view.entities;


public class Aluno {

    private String nome;
    private String email;
    private String laboratorio;
    private String curso;
    private String telefone;



    public Aluno(String nome, String email, String laboratorio, String curso) {
        this.nome = nome;
        this.email = email;
        this.laboratorio = laboratorio;
        this.curso = curso;
    }
    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
