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
import android.widget.ProgressBar;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.Horario;
import com.devdes.allon.models.HorarioAdapter;
import com.devdes.allon.models.ObjetosApi;

import java.util.ArrayList;


public class HorarioAulaFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    private class PegaHorarioTask extends AsyncTask<Void, Void, ArrayList<Horario>> {
        @Override
        protected void onPreExecute() {
            pegaHorariosInicio();
        }

        @Override
        protected ArrayList<Horario> doInBackground(Void... voids) {
            return pegaHorariosCorpo();
        }

        @Override
        protected void onPostExecute(ArrayList<Horario> horarios) {
            pegaHorariosSucesso(horarios);
        }
    }

    private View view;
    private Integer COUNT = 0;
    private final Integer MAX = 5;

    public HorarioAulaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_horario_aula, container, false);

        Button btnHorTentarNovamente = view.findViewById(R.id.btnHorTentarNovamente);

        btnHorTentarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PegaHorarioTask().execute();
            }
        });

        new PegaHorarioTask().execute();

        return view;
    }

    private void pegaHorariosInicio() {
        mostraLoad(true);
    }

    private void pegaHorariosSucesso(ArrayList<Horario> horarios) {
        if(horarios == null && COUNT < MAX){
            COUNT++;
            new PegaHorarioTask().execute();
            return;
        }
        if(horarios == null){
            mostraTentarNovamente(true);
            return;
        }

        mostraTentarNovamente(false);

        RecyclerView rvHorario = view.findViewById(R.id.rvHorario);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(
                view.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        rvHorario.setAdapter(new HorarioAdapter(horarios, view.getContext()));
        rvHorario.setLayoutManager(layout);
    }

    private ArrayList<Horario> pegaHorariosCorpo() {
        AlunoOnlineApi alunoOnlineApi = new AlunoOnlineApi();

        ObjetosApi.RespostaLogin respostaLogin;

        Bundle bundle = getArguments();

        respostaLogin = new ObjetosApi.RespostaLogin(
                bundle.getString(getString(R.string.const_login_token)),
                bundle.getInt(getString(R.string.const_login_identificador))
        );

        return alunoOnlineApi.horarios(respostaLogin);

    }

    private void mostraLoad(Boolean bool) {
        ProgressBar load = view.findViewById(R.id.horarioLoad);

        load.setVisibility(bool ? View.VISIBLE : View.INVISIBLE);
    }

    private void mostraTentarNovamente(Boolean bool) {
        LinearLayout tentarNovamente = view.findViewById(R.id.horarioTentarNovamente);
        RecyclerView rvHorario = view.findViewById(R.id.rvHorario);

        tentarNovamente.setVisibility(bool ? View.VISIBLE : View.INVISIBLE);
        rvHorario.setVisibility(bool ? View.INVISIBLE : View.VISIBLE);

        mostraLoad(false);
    }

}
