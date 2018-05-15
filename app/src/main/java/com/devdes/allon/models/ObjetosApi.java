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
}
