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

import android.widget.*;
import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.ConteudoAula;
import com.devdes.allon.models.ConteudoAulaAdapter;
import com.devdes.allon.models.ObjetosApi;

import java.util.ArrayList;


public class ConteudoAulaFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    private class PegaConteudoAulaTask extends AsyncTask<Void, Void, ArrayList<ConteudoAula>> {
        @Override
        protected void onPreExecute() {
            pegaConteudoAulaInicio();
        }

        @Override
        protected ArrayList<ConteudoAula> doInBackground(Void... voids) {
            return pegaConteudoAulaCorpo();
        }

        @Override
        protected void onPostExecute(ArrayList<ConteudoAula> conteudosAula) {
            pegaConteudoAulaSucesso(conteudosAula);
        }
    }

    private View view;

    private Integer COUNT = 0;
    private final Integer MAX = 5;

    public ConteudoAulaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_conteudo_aula, container, false);

        Button btnConteudoTentarNovamente = view.findViewById(R.id.btnConteudoTentarNovamente);

        btnConteudoTentarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PegaConteudoAulaTask().execute();

            }
        });

        new PegaConteudoAulaTask().execute();

        return view;
    }

    private void pegaConteudoAulaInicio() {
        mostraLoad(true);
    }

    private void pegaConteudoAulaSucesso(final ArrayList<ConteudoAula> conteudoAulas) {

        ArrayList<String> listaDisciplina;


        listaDisciplina = new ArrayList<>();

        if(conteudoAulas == null && COUNT < MAX){
            COUNT++;
            new PegaConteudoAulaTask().execute();
            return;
        }

        if(conteudoAulas == null){
            mostraTentarNovamente(true);
            return;
        }

        mostraTentarNovamente(false);

        for (ConteudoAula ca: conteudoAulas) {
            listaDisciplina.add(ca.getAula());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                android.R.layout.simple_list_item_1, listaDisciplina);

        Spinner sConteudoAula = view.findViewById(R.id.sConteudoAula);

        sConteudoAula.setAdapter(adapter);

        sConteudoAula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                atualizaListaConteudo(conteudoAulas.get(i).getConteudos());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private ArrayList<ConteudoAula> pegaConteudoAulaCorpo() {
        AlunoOnlineApi alunoOnlineApi = new AlunoOnlineApi();

        ObjetosApi.RespostaLogin respostaLogin;

        Bundle bundle = getArguments();

        respostaLogin = new ObjetosApi.RespostaLogin(
                bundle.getString(getString(R.string.const_login_token)),
                bundle.getInt(getString(R.string.const_login_identificador))
        );

        return alunoOnlineApi.conteudoAula(respostaLogin);
    }

    private void mostraLoad(Boolean bool) {
        ProgressBar load = view.findViewById(R.id.loadConteudoAula);

        load.setVisibility(bool ? View.VISIBLE : View.INVISIBLE);
    }

    private void mostraTentarNovamente(Boolean bool) {
        LinearLayout tentarNovamente = view.findViewById(R.id.conteudoAulaTentarNovamente);
        LinearLayout llConteudoAula = view.findViewById(R.id.llConteudoAula);

        tentarNovamente.setVisibility(bool ? View.VISIBLE : View.INVISIBLE);
        llConteudoAula.setVisibility(bool ? View.INVISIBLE : View.VISIBLE);

        mostraLoad(false);
    }


    private void atualizaListaConteudo(ArrayList<ConteudoAula.Conteudo> conteudos) {
        RecyclerView rvConteudoAula = view.findViewById(R.id.rvConteudoAula);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(
                view.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        rvConteudoAula.setAdapter(new ConteudoAulaAdapter(conteudos, view.getContext()));
        rvConteudoAula.setLayoutManager(layout);
    }

}
