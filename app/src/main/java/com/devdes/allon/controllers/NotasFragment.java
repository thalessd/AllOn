package com.devdes.allon.controllers;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.Nota;
import com.devdes.allon.models.NotaAdapter;
import com.devdes.allon.models.ObjetosApi;

import java.util.ArrayList;

public class NotasFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    private class PegaNotaTask extends AsyncTask<Void, Void, ArrayList<Nota>> {
        @Override
        protected void onPreExecute() {
            pegaDadosInicio();
        }

        @Override
        protected ArrayList<Nota> doInBackground(Void... voids) {
            return pegaNotasCorpo();
        }

        @Override
        protected void onPostExecute(ArrayList<Nota> notas) {
            pegaNotasSucesso(notas);
        }
    }

    View view;
    Integer tentativas = 0;

    public NotasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notas, container, false);

        Button btnNotaTentarNovamente = view.findViewById(R.id.btnNotaTentarNovamente);

        btnNotaTentarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PegaNotaTask().execute();
            }
        });

        new PegaNotaTask().execute();

        return view;
    }

    private void pegaDadosInicio() {
        mostraLoad(true);
    }

    private ArrayList<Nota> pegaNotasCorpo() {
        AlunoOnlineApi alunoOnlineApi = new AlunoOnlineApi();

        ObjetosApi.RespostaLogin respostaLogin;

        Bundle bundle = getArguments();

        respostaLogin = new ObjetosApi.RespostaLogin(
                bundle.getString(getString(R.string.const_login_token)),
                bundle.getInt(getString(R.string.const_login_identificador))
        );

        ArrayList<Nota> notas = alunoOnlineApi.notas(respostaLogin);

        return notas;
    }

    private void pegaNotasSucesso(ArrayList<Nota> notas) {

        mostraLoad(false);

        if(notas == null) {
            if(tentativas < 5) {
                tentativas++;
                new PegaNotaTask().execute();
                return;
            }

            mostraTentarNovamente();
            return;
        }

        tentativas = 0;

        RecyclerView.LayoutManager layout = new LinearLayoutManager(
                view.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        RecyclerView rvNotas = view.findViewById(R.id.rvNotas);

        rvNotas.setAdapter(new NotaAdapter(notas, view.getContext()));
        rvNotas.setLayoutManager(layout);

    }

    private void mostraLoad(Boolean bool) {
        LinearLayout llLoad = view.findViewById(R.id.notaLoad);
        ScrollView svNota = view.findViewById(R.id.containerNotas);
        LinearLayout llTentarNovamente = view.findViewById(R.id.notaTentarNovamente);

        llLoad.setVisibility(bool ? View.VISIBLE : View.INVISIBLE);
        svNota.setVisibility(bool ? View.INVISIBLE : View.VISIBLE);
        llTentarNovamente.setVisibility(View.INVISIBLE);

    }

    private void mostraTentarNovamente() {
        LinearLayout llTentarNovamente = view.findViewById(R.id.notaTentarNovamente);
        LinearLayout llLoad = view.findViewById(R.id.notaLoad);
        ScrollView svNota = view.findViewById(R.id.containerNotas);

        llTentarNovamente.setVisibility(View.VISIBLE);

        svNota.setVisibility(View.INVISIBLE);
        llLoad.setVisibility(View.INVISIBLE);
    }
}
