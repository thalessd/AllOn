package com.devdes.allon.models;

public class Horario {

    public class Hora {
        private String primeiraAula;
        private String segundaAula;
        private String terceiraAula;
        private String quartaAula;

        public String getPrimeiraAula() {
            return primeiraAula;
        }

        public void setPrimeiraAula(String primeiraAula) {
            this.primeiraAula = primeiraAula;
        }

        public String getSegundaAula() {
            return segundaAula;
        }

        public void setSegundaAula(String segundaAula) {
            this.segundaAula = segundaAula;
        }

        public String getTerceiraAula() {
            return terceiraAula;
        }

        public void setTerceiraAula(String terceiraAula) {
            this.terceiraAula = terceiraAula;
        }

        public String getQuartaAula() {
            return quartaAula;
        }

        public void setQuartaAula(String quartaAula) {
            this.quartaAula = quartaAula;
        }
    }

    private String nomeDiaSemana;

    private String primeiraAula;
    private String segundaAula;
    private String terceiraAula;
    private String quartaAula;

    private Hora hora;

    public String getNomeDiaSemana() {
        return nomeDiaSemana;
    }

    public void setNomeDiaSemana(String nomeDiaSemana) {
        this.nomeDiaSemana = nomeDiaSemana;
    }

    public String getPrimeiraAula() {
        return primeiraAula;
    }

    public void setPrimeiraAula(String primeiraAula) {
        this.primeiraAula = primeiraAula;
    }

    public String getSegundaAula() {
        return segundaAula;
    }

    public void setSegundaAula(String segundaAula) {
        this.segundaAula = segundaAula;
    }

    public String getTerceiraAula() {
        return terceiraAula;
    }

    public void setTerceiraAula(String terceiraAula) {
        this.terceiraAula = terceiraAula;
    }

    public String getQuartaAula() {
        return quartaAula;
    }

    public void setQuartaAula(String quartaAula) {
        this.quartaAula = quartaAula;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }
}
