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
import android.widget.TextView;

import com.devdes.allon.R;
import com.devdes.allon.models.AlunoOnlineApi;
import com.devdes.allon.models.ObjetosApi;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeScreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
            if(respostaPegaDados != null) {
                pegaDadosSucesso(respostaPegaDados);
            }
        }
    }

    // Cria nova task asincrona
    private class CarregaFotoTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected void onPreExecute() { pegaDadosInicio(); }
        @Override
        protected Bitmap doInBackground(Void... voids) {

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
        @Override
        protected void onPostExecute(Bitmap foto) {
           if(foto != null) {
               defineFotoUsuario(foto);
           }
        }
    }

    View view;
    String urlFotoUsuario;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeScreenFragment newInstance(String param1, String param2) {
        HomeScreenFragment fragment = new HomeScreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        Button btnMeusDados = view.findViewById(R.id.btnMeusDados);

        new PegaDadosTask().execute();

        btnMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MeusDadosActivity.class));
            }
        });

        return view;
    }

    // Funções

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

        StringBuilder sb = new StringBuilder();

        TextView nomeAluno = view.findViewById(R.id.tvNomeAluno);
        nomeAluno.setText(meusDados.nome);


        TextView curso = view.findViewById(R.id.tvCurso);
        curso.setText(meusDados.curso);


        TextView turmaMatricula = view.findViewById(R.id.tvTurmaMatricula);

        sb.append(meusDados.turma);
        sb.append(" - ");
        sb.append(meusDados.matricula);

        turmaMatricula.setText(sb.toString());

        urlFotoUsuario = meusDados.urlFoto;
        new CarregaFotoTask().execute();


    }

    private void pegaDadosInicio(){

    }


    private void defineFotoUsuario(Bitmap foto) {
        CircleImageView imgUser = view.findViewById(R.id.cimFotoPerfil);
        imgUser.setImageBitmap(foto);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
