package com.devdes.allon.models;


import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.io.EOFException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

public class AlunoOnlineApi {

    // Urls gerais
    private final String URL_API_GERAL = "http://aluno-online.devdes.com.br";
    private final String URL_API_USUARIO_RSA = URL_API_GERAL + "/usuariorsa";
    private final String URL_API_USUARIO_RSA_AUTH = URL_API_USUARIO_RSA + "/auth";

    // Urls Especificas
    private final String URL_API_LOGIN = URL_API_USUARIO_RSA + "/login";

    // Client
    OkHttpClient client;

    public AlunoOnlineApi() {
        this.client = new OkHttpClient();
    }

    private String requestPost(String url, String json){

        ResponseBody resBody;
        MediaType JSON;
        Request.Builder builder;
        RequestBody body;
        Request request;
        Response response;

        JSON = MediaType.parse("application/json; charset=utf-8");

        body = RequestBody.create(JSON, json);

        builder = new Request.Builder();

        builder.url(url);
        builder.post(body);

        request = builder.build();

        try {

            response = this.client.newCall(request).execute();

            resBody = response.body();

            return resBody != null ? resBody.string() : null;

        }catch (Exception e){
            return null;
        }

    }

    public ResponseData.ResLogin login(Integer matricula, String senha) {

        String respJson;
        JsonObject loginReq;
        JsonObject loginRes;


        loginReq = new JsonObject();

        loginReq.add("matricula", matricula);
        loginReq.add("senha", senha);

        respJson = requestPost(URL_API_LOGIN, loginReq.toString());

        if(respJson == null) {
            return null;
        }

        loginRes = JsonObject.readFrom(respJson);

        return new ResponseData.ResLogin(
                loginRes.get("dado").asObject().get("token").asString(),
                loginRes.get("dado").asObject().get("identificador").asInt()
        );

    }


}
