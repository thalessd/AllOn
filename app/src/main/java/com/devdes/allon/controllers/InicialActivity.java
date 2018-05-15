package com.devdes.allon.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.ObjetosApi;

public class InicialActivity extends AppCompatActivity{

    // Cria nova task asincrona
    private class BuscarDadosCadastrais extends AsyncTask<Void, Void, ObjetosApi.RespostaLogin> {
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected ObjetosApi.RespostaLogin doInBackground(Void... voids) {

            dadosCadastraisCorpo();

            return new ObjetosApi.RespostaLogin("sdasd", 123);
        }
        @Override
        protected void onPostExecute(ObjetosApi.RespostaLogin respostaLogin) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        // btn.setOnClickListener(new View.OnClickListener() {
        //     @Override public void onClick(View view) {
        //         System.out.println("Clicked");
        //     }
        // });

    }

    private  void dadosCadastraisCorpo() {
        AlunoOnlineApi alunoOnlineApi = new AlunoOnlineApi();
    }
}
