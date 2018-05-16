package com.devdes.allon.controllers;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.devdes.allon.R;
import com.devdes.allon.models.ObjetosApi;
import com.devdes.allon.models.Preferencias;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler;

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                defineProxTela();
            }
        }, 1000);


    }

    private void defineProxTela() {
        ObjetosApi.RespostaLogin respostaLogin;
        Preferencias preferencias;
        Intent intent;


        preferencias = new Preferencias();
        respostaLogin = preferencias.pegaLoginRespSalvado(this);

        if(respostaLogin != null){
            intent = new Intent(this, PanelActivity.class);

            intent.putExtra(getString(R.string.const_login_token),
                    respostaLogin.getToken());
            intent.putExtra(getString(R.string.const_login_identificador),
                    respostaLogin.getIdentificador());
        }else {
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);

        finish();
    }
}
