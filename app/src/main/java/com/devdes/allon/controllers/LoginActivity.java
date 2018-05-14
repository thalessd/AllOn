package com.devdes.allon.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                fazLogin();
            }
        });

    }

    public void fazLogin() {
        Intent intent = new Intent(this, PanelActivity.class);

        new Thread() {

            public void run() {

                AlunoOnlineApi alunoOnlineApi = new AlunoOnlineApi();

                alunoOnlineApi.login(17202001, "G3@qwerty5");
            }

        }.start();

        startActivity(intent);

    }
}
