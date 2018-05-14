package com.devdes.allon.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.ResponseData;

public class LoginActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;

    // Cria nova task asincrona
    private class FazerLoginTask extends AsyncTask<Void, Void, ResponseData.ResLogin> {

        @Override
        protected void onPreExecute() {
            loginLoad(true);
        }

        @Override
        protected ResponseData.ResLogin doInBackground(Void... voids) {

            ResponseData.ResLogin resLogin;
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

            resLogin = alunoOnlineApi.login(Integer.parseInt(matricula), senha);

            if(resLogin == null) {

                snackbar = Snackbar.make(
                        coordinatorLayout,
                        R.string.login_invalido,
                        Snackbar.LENGTH_LONG
                );

                snackbar.show();

                return null;
            }

            return resLogin;
        }

        protected void onPostExecute(ResponseData.ResLogin resLogin) {

            loginLoad(false);

            if(resLogin != null) loginSucesso(resLogin);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btnLogin);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                fazLogin();
            }
        });

    }

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

    // Métodos
    private void loginSucesso(ResponseData.ResLogin resLogin) {
        Intent intent;
        /*
         * CRIANDO PASSAGEM DE PARÂMETROS PARA A TELA DE LOGIN;
         */
        intent = new Intent(this, PanelActivity.class);
        startActivity(intent);
        finish();
    }

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
}
