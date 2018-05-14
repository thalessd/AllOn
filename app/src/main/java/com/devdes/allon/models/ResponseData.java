package com.devdes.allon.models;

public class ResponseData {
    public static class ResLogin {

        String token;
        Integer identificador;

        public ResLogin(String token, Integer identificador) {
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
