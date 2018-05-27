package com.devdes.allon.models;

import java.util.ArrayList;

public class ConteudoAula {

    public static class Conteudo {
        private String data;
        private String inicio;
        private String termino;
        private String conteudo;
        private String falta;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getInicio() {
            return inicio;
        }

        public void setInicio(String inicio) {
            this.inicio = inicio;
        }

        public String getTermino() {
            return termino;
        }

        public void setTermino(String termino) {
            this.termino = termino;
        }

        public String getConteudo() {
            return conteudo;
        }

        public void setConteudo(String conteudo) {
            this.conteudo = conteudo;
        }

        public String getFalta() {
            return falta;
        }

        public void setFalta(String falta) {
            this.falta = falta;
        }
    }

    private String aula;
    private Integer falta;
    private ArrayList<Conteudo> conteudos;


    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Integer getFalta() {
        return falta;
    }

    public void setFalta(Integer falta) {
        this.falta = falta;
    }

    public ArrayList<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(ArrayList<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }
}
