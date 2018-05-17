package com.devdes.allon.models;

public class ObjetosApi {
    public static class RespostaLogin {

        String token;
        Integer identificador;

        public RespostaLogin(String token, Integer identificador) {
            this.token = token;
            this.identificador = identificador;
        }

        public String getToken() {
            return token;
        }

        public Integer getIdentificador() {
            return identificador;
        }
    }

    public static class RespostaMeusDados {

        public String urlFoto;
        public String nome;
        public String curso;
        public String turma;
        public String matricula;
        public String nomeSocial;
        public String dataNascimento;
        public String estadoCivil;
        public String nomePai;
        public String nomeMae;
        public String corRaca;
        public String profissao;
        public String localTrabalho;
        public String naturalidade;
        public String nacionalidade;
        public String endereco;
        public String numero;
        public String bairro;
        public String complemento;
        public String cep;
        public String cidade;
        public String estado;
        public String telTrabalho;
        public String telResidencia;
        public String telCelular;
        public String telResponsavel;
        public String email;
        public String RG;
        public String CPF;
        public String orgExp;

    }
}
