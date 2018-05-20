package com.devdes.allon.models;

public class Nota {
    private Integer codigo;
    private String disciplina;
    private Double primeiraNota;
    private Double segundaNota;
    private Double terceiraNota;
    private Double mediaAtual;
    private Double media;
    private Double provaFinalNota;
    private Integer quantidadeFalta;
    private Integer faltaPercentual;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Double getPrimeiraNota() {
        return primeiraNota;
    }

    public void setPrimeiraNota(Double primeiraNota) {
        this.primeiraNota = primeiraNota;
    }

    public Double getSegundaNota() {
        return segundaNota;
    }

    public void setSegundaNota(Double segundaNota) {
        this.segundaNota = segundaNota;
    }

    public Double getTerceiraNota() {
        return terceiraNota;
    }

    public void setTerceiraNota(Double terceiraNota) {
        this.terceiraNota = terceiraNota;
    }

    public Double getMediaAtual() {
        return mediaAtual;
    }

    public void setMediaAtual(Double mediaAtual) {
        this.mediaAtual = mediaAtual;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Double getProvaFinalNota() {
        return provaFinalNota;
    }

    public void setProvaFinalNota(Double provaFinalNota) {
        this.provaFinalNota = provaFinalNota;
    }

    public Integer getQuantidadeFalta() {
        return quantidadeFalta;
    }

    public void setQuantidadeFalta(Integer quantidadeFalta) {
        this.quantidadeFalta = quantidadeFalta;
    }

    public Integer getFaltaPercentual() {
        return faltaPercentual;
    }

    public void setFaltaPercentual(Integer faltaPercentual) {
        this.faltaPercentual = faltaPercentual;
    }
}
