package com.devdes.allon.controllers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.Informativo;
import com.devdes.allon.models.InformativoAdapter;
import com.devdes.allon.models.ObjetosApi;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeScreenFragment extends Fragment{

    // Cria nova task asincrona
    @SuppressLint("StaticFieldLeak")
    private class PegaDadosTask extends AsyncTask<Void, Void, ObjetosApi.RespostaMeusDados> {
        @Override
        protected void onPreExecute() { pegaDadosInicio(); }
        @Override
        protected ObjetosApi.RespostaMeusDados doInBackground(Void... voids) {
            return pegaDadosCorpo();
        }
        @Override
        protected void onPostExecute(ObjetosApi.RespostaMeusDados respostaPegaDados) {

            // Finaliza o load no card
            cabecalhoLoad(false);

            // verifica se conseguiu pegar
            // os dados ou não
            if(respostaPegaDados != null) {
                cabecalhoNaoCarregado(false);
                pegaDadosSucesso(respostaPegaDados);
            }else cabecalhoNaoCarregado(true);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class CarregaFotoTask extends AsyncTask<Void, Void, Bitmap> {

        private String urlFotoUsuario;

        public CarregaFotoTask(String urlFotoUsuario) {
            this.urlFotoUsuario = urlFotoUsuario;
        }

        @Override
        protected void onPreExecute() {  }
        @Override
        protected Bitmap doInBackground(Void... voids) {
            return carregaImagemDoServidor(this.urlFotoUsuario);
        }
        @Override
        protected void onPostExecute(Bitmap foto) {
            if(foto != null)
                defineFotoUsuario(foto);
        }
    }

    // Cria nova task asincrona
    @SuppressLint("StaticFieldLeak")
    private class PegaInformativosTask extends AsyncTask<Void, Void, ArrayList<Informativo>> {

        @Override
        protected void onPreExecute() {
            pegaInformativosInicio();
        }

        @Override
        protected ArrayList<Informativo> doInBackground(Void... voids) {
            return pegaInformativosCorpo();
        }

        @Override
        protected void onPostExecute(ArrayList<Informativo> informativos) {
            pegaInformativosSucesso(informativos);
        }
    }

    private View view;
    private ObjetosApi.RespostaMeusDados meusDados;
    private ArrayList<Informativo> informativosGlob;

    private Integer contador = 0;

    public HomeScreenFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        final SwipeRefreshLayout srl = view.findViewById(R.id.swipeLoad);
        Button btnMeusDados = view.findViewById(R.id.btnMeusDados);
        Button btnTentarNovamente = view.findViewById(R.id.btnTentarNovamente);

        btnMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;

                MeusDadosActivity.meusDados = meusDados;

                intent = new Intent(view.getContext(), MeusDadosActivity.class);

                startActivity(intent);
            }
        });

        btnTentarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PegaInformativosTask().execute();
            }
        });

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new PegaDadosTask().execute();
                new PegaInformativosTask().execute();

                srl.setRefreshing(false);
            }
        });

        new PegaDadosTask().execute();
        new PegaInformativosTask().execute();

        return view;
    }

    // Pega dados Funções
    private void pegaDadosInicio(){
        cabecalhoLoad(true);
    }

    private ObjetosApi.RespostaMeusDados pegaDadosCorpo() {
        Bundle bundle = getArguments();

        AlunoOnlineApi alunoOnlineApi = new AlunoOnlineApi();

        ObjetosApi.RespostaLogin respostaLogin;

        respostaLogin = new ObjetosApi.RespostaLogin(
                bundle.getString(getString(R.string.const_login_token)),
                bundle.getInt(getString(R.string.const_login_identificador))
        );

        return alunoOnlineApi.dadosCadastrais(respostaLogin);
    }

    private void pegaDadosSucesso(ObjetosApi.RespostaMeusDados meusDados){

        StringBuilder sb;

        TextView nomeAluno = view.findViewById(R.id.tvNomeAluno);
        nomeAluno.setText(meusDados.nome);


        TextView curso = view.findViewById(R.id.tvCurso);
        curso.setText(meusDados.curso);


        TextView turmaMatricula = view.findViewById(R.id.tvTurmaMatricula);

        sb = new StringBuilder();

        sb.append(meusDados.turma);
        sb.append(" - ");
        sb.append(meusDados.matricula);

        turmaMatricula.setText(sb.toString());

        iniciaCarregamentoFotoUsuario(meusDados.urlFoto);

        this.meusDados = meusDados;

    }

    private void cabecalhoLoad(Boolean load){
        view.findViewById(R.id.llViewCabecalho).setVisibility(load ? View.INVISIBLE : View.VISIBLE);
        view.findViewById(R.id.llLoadCabecalho).setVisibility(load ? View.VISIBLE : View.GONE);
    }

    private void cabecalhoNaoCarregado(Boolean bool){

        view.findViewById(R.id.llViewCabecalho).setVisibility(
                bool ? View.INVISIBLE
                : view.findViewById(R.id.llViewCabecalho).getVisibility()
        );

        view.findViewById(R.id.llLoadCabecalho).setVisibility(
                bool ? View.INVISIBLE
                : view.findViewById(R.id.llLoadCabecalho).getVisibility()
        );

        view.findViewById(R.id.llNoConnectCabecalho).setVisibility(bool ? View.VISIBLE : View.INVISIBLE);

    }


    // Foto do usuário
    private Bitmap carregaImagemDoServidor(String urlFotoUsuario){
        try {
            URL url = new URL(urlFotoUsuario);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.connect();

            InputStream input = connection.getInputStream();

            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            return null;
        }
    }

    private void defineFotoUsuario(Bitmap foto) {
        CircleImageView imgUser = view.findViewById(R.id.cimFotoPerfil);

        if(foto == null){
            imgUser.setImageResource(R.drawable.img_livros);
        }

        imgUser.setImageBitmap(foto);
    }

    private void iniciaCarregamentoFotoUsuario(String urlFotoUsuario){


        CarregaFotoTask carregaFotoTask;

        carregaFotoTask = new CarregaFotoTask(urlFotoUsuario);

        carregaFotoTask.execute();
    }

    // Informativos
    private void pegaInformativosInicio() {
        mostraLoadInformativo(true);
    }

    private ArrayList<Informativo> pegaInformativosCorpo() {
        Bundle bundle = getArguments();

        AlunoOnlineApi alunoOnlineApi = new AlunoOnlineApi();

        ObjetosApi.RespostaLogin respostaLogin;

        respostaLogin = new ObjetosApi.RespostaLogin(
                bundle.getString(getString(R.string.const_login_token)),
                bundle.getInt(getString(R.string.const_login_identificador))
        );

        return alunoOnlineApi.informativos(respostaLogin);
    }

    private void pegaInformativosSucesso(ArrayList<Informativo> informativos) {
        final RecyclerView homeListaInformativo = view.findViewById(R.id.homeListaInformativo);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(
                view.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        mostraLoadInformativo(false);

        if(informativos != null) {
            informativosGlob = informativos;
            contador = 0;

            informativoNaoCarregado(false);

            homeListaInformativo.setAdapter(
                    new InformativoAdapter(
                            informativos,
                            view.getContext()
                    )
            );

            homeListaInformativo.setHasFixedSize(true);

            homeListaInformativo.setLayoutManager(layout);



        }else {

            if(contador < 5) {
                contador++;
                new PegaInformativosTask().execute();
                return;
            }

            informativoNaoCarregado(true);
        }

    }

    private void mostraLoadInformativo(Boolean bool) {
        view.findViewById(R.id.informativoProgress).setVisibility(bool ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.homeListaInformativo).setVisibility(bool ? View.GONE : View.VISIBLE);
        view.findViewById(R.id.erroInformativo).setVisibility(View.GONE);
    }

    private void informativoNaoCarregado(Boolean bool) {

        view.findViewById(R.id.erroInformativo).setVisibility(bool ? View.VISIBLE : View.GONE);

        view.findViewById(R.id.homeListaInformativo).setVisibility(
                bool ? View.GONE : View.VISIBLE
        );

    }


}
