package com.devdes.allon.models;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.devdes.allon.R;

public class Preferencias {
    public void salvaLoginResp(ObjetosApi.RespostaLogin respostaLogin, Activity activity) {
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;


        sharedPreferences = activity.getSharedPreferences(
                activity.getString(R.string.pref_login),
                Context.MODE_PRIVATE
        );

        editor = sharedPreferences.edit();

        editor.putString("token", respostaLogin.getToken());
        editor.putInt("identificador", respostaLogin.getIdentificador());

        editor.apply();

    }

    public ObjetosApi.RespostaLogin pegaLoginRespSalvado(Activity activity) {

        SharedPreferences sharedPreferences;

        Integer identificador;
        String token;

        sharedPreferences = activity.getSharedPreferences(
                activity.getString(R.string.pref_login),
                Context.MODE_PRIVATE
        );

        token = sharedPreferences.getString("token", "");
        identificador = sharedPreferences.getInt("identificador", 0);

        if(token.isEmpty() || identificador == 0) {
            return null;
        }

        return new ObjetosApi.RespostaLogin(token, identificador);
    }
}
