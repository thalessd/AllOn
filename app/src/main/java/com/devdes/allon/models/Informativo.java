package com.devdes.allon.models;

import java.util.ArrayList;

public class Informativo {

    private String data;
    private String titulo;
    private String professor;
    private String descricao;
    private ArrayList<String> anexos;

    public Informativo(String data, String titulo, String professor, String descricao, ArrayList<String> anexos) {
        this.data = data;
        this.titulo = titulo;
        this.professor = professor;
        this.descricao = descricao;
        this.anexos = anexos;
    }

    public String getData() {
        return data;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getProfessor() {
        return professor;
    }

    public String getDescricao() {
        return descricao;
    }

    public ArrayList<String> getAnexos() {
        return anexos;
    }
}
