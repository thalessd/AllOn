package com.devdes.allon.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.ObjetosApi;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeScreenFragment extends Fragment {

    // Cria nova task asincrona
    private class PegaDadosTask extends AsyncTask<Void, Void, ObjetosApi.RespostaMeusDados> {
        @Override
        protected void onPreExecute() { pegaDadosInicio(); }
        @Override
        protected ObjetosApi.RespostaMeusDados doInBackground(Void... voids) {
            return pegaDadosCorpo();
        }
        @Override
        protected void onPostExecute(ObjetosApi.RespostaMeusDados respostaPegaDados) {
            cabecalhoLoad(false);

            if(respostaPegaDados != null) {
                pegaDadosSucesso(respostaPegaDados);
            }
        }
    }

    private class CarregaFotoTask extends AsyncTask<Void, Void, Bitmap> {

        private String urlFotoUsuario;

        public CarregaFotoTask(String urlFotoUsuario) {
            this.urlFotoUsuario = urlFotoUsuario;
        }

        @Override
        protected void onPreExecute() { pegaDadosInicio(); }
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

    View view;

    public HomeScreenFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        Button btnMeusDados = this.view.findViewById(R.id.btnMeusDados);

        new PegaDadosTask().execute();

        btnMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MeusDadosActivity.class));
            }
        });

        return this.view;
    }


    // Pega dados Funções
    private void pegaDadosInicio(){
        // cabecalhoLoad(true);
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

        alimentaMeusDadosActivity(meusDados);

    }

    private void alimentaMeusDadosActivity(ObjetosApi.RespostaMeusDados meusDados) {

    }

    private void cabecalhoLoad(Boolean load){
        LinearLayout llLoadCabecalho = view.findViewById(R.id.llLoadCabecalho);
        LinearLayout llViewCabecalho = view.findViewById(R.id.llViewCabecalho);


        llLoadCabecalho.setVisibility(load ? View.VISIBLE : View.GONE);
        llViewCabecalho.setVisibility(load ? View.INVISIBLE : View.VISIBLE);

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

}
