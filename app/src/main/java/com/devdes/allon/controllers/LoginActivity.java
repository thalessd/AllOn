package com.devdes.allon.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.ObjetosApi;
import com.devdes.allon.models.Preferencias;

public class LoginActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    Preferencias preferencias;

    // Cria nova task asincrona
    private class FazerLoginTask extends AsyncTask<Void, Void, ObjetosApi.RespostaLogin> {
        @Override
        protected void onPreExecute() {
            loginLoad(true);
        }
        @Override
        protected ObjetosApi.RespostaLogin doInBackground(Void... voids) {
            return loginCorpo();
        }
        @Override
        protected void onPostExecute(ObjetosApi.RespostaLogin respostaLogin) {
            if(respostaLogin != null) loginSucesso(respostaLogin);
            loginLoad(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencias = new Preferencias();

        paraPainel();


        Button btnLogin = findViewById(R.id.btnLogin);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                fazLogin();
            }
        });

    }

    // Funções
    private void fazLogin() {
        Snackbar snackbar = Snackbar.make(
                coordinatorLayout,
                R.string.login_invalido,
                Snackbar.LENGTH_LONG
        );

        if(formLoginInvalido()){
            snackbar.show();
            return;
        }

        new FazerLoginTask().execute();
    }

    private ObjetosApi.RespostaLogin loginCorpo() {

        ObjetosApi.RespostaLogin respostaLogin;
        AlunoOnlineApi alunoOnlineApi;

        EditText matriculaLogin;
        EditText senhaLogin;

        String matricula;
        String senha;

        Snackbar snackbar;

        // Define a api de login
        alunoOnlineApi = new AlunoOnlineApi();

        matriculaLogin = findViewById(R.id.matriculaLogin);
        senhaLogin = findViewById(R.id.senhaLogin);

        matricula = matriculaLogin.getText().toString();
        senha = senhaLogin.getText().toString();

        respostaLogin = alunoOnlineApi.login(Integer.parseInt(matricula), senha);

        if(respostaLogin == null) {

            snackbar = Snackbar.make(
                    coordinatorLayout,
                    R.string.login_invalido,
                    Snackbar.LENGTH_LONG
            );

            snackbar.show();

            return null;
        }

        return respostaLogin;
    }

    private void loginSucesso(ObjetosApi.RespostaLogin respostaLogin) {
        preferencias.salvaLoginResp(respostaLogin, this);
        paraPainel();
    }

    // Utils
    private void loginLoad(Boolean load) {
        findViewById(R.id.llLoginForm).setVisibility(load ? View.GONE : View.VISIBLE);
        findViewById(R.id.loadLogin).setVisibility(load ? View.VISIBLE : View.GONE);
    }

    private Boolean formLoginInvalido() {
        EditText matriculaLogin;
        EditText senhaLogin;

        matriculaLogin = findViewById(R.id.matriculaLogin);
        senhaLogin = findViewById(R.id.senhaLogin);

        // Verifica se os campos são vazios e se a matricula é incorreta
        return matriculaLogin.length() != 8 ||
                (matriculaLogin.getText().toString().isEmpty()
                        || senhaLogin.getText().toString().isEmpty());

    }

    private void paraPainel() {
        if(preferencias.pegaLoginRespSalvado(this) != null){
            startActivity(new Intent(this, PanelActivity.class));
            finish();
        }
    }
}
