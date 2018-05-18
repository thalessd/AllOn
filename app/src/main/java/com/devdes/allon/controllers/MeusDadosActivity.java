package com.devdes.allon.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devdes.allon.R;
import com.devdes.allon.models.ObjetosApi;

import java.util.ArrayList;

public class MeusDadosActivity extends AppCompatActivity {

    public class DadoUsuarioHolder extends RecyclerView.ViewHolder {

        final TextView itemChave;
        final TextView itemValor;

        public DadoUsuarioHolder(View itemView) {
            super(itemView);

            itemChave = itemView.findViewById(R.id.itemChave);
            itemValor = itemView.findViewById(R.id.itemValor);

        }
    }

    private class DadoUsuarioAdapter extends RecyclerView.Adapter {

        private ArrayList<String[]> dados;
        private Context context;

        public DadoUsuarioAdapter(ArrayList<String[]> dados, Context context) {
            this.dados = dados;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_dado_aluno, parent, false);

            return new DadoUsuarioHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            DadoUsuarioHolder viewHolder = (DadoUsuarioHolder) holder;

            String[] dado = dados.get(position);

            if(dado[1].isEmpty()){
                dado[1] = "Não Informado";
            }

            viewHolder.itemChave.setText(dado[0]);
            viewHolder.itemValor.setText(dado[1]);

        }

        @Override
        public int getItemCount() {
            return dados.size();
        }
    }


    public static ObjetosApi.RespostaMeusDados meusDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        chamaReciclerView();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void chamaReciclerView() {
        RecyclerView rvListaDado = findViewById(R.id.rvListaDado);
        ArrayList<String[]> listaDados = criaMatrizDeDados(meusDados);

        rvListaDado.setAdapter(new DadoUsuarioAdapter(listaDados, this));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );

        rvListaDado.setLayoutManager(layout);

    }

    private ArrayList<String[]> criaMatrizDeDados(ObjetosApi.RespostaMeusDados meusDado){
        ArrayList<String[]> dados = new ArrayList<>();

        dados.add(new String[]{"Nome", meusDado.nome});
        dados.add(new String[]{"Curso", meusDado.curso});
        dados.add(new String[]{"Turma", meusDado.turma});
        dados.add(new String[]{"Nome Social", meusDado.nomeSocial});
        dados.add(new String[]{"Data de Nascimento", meusDado.dataNascimento});
        dados.add(new String[]{"RG", meusDado.RG});
        dados.add(new String[]{"CPF", meusDado.CPF});
        dados.add(new String[]{"Org. Expedição", meusDado.orgExp});
        dados.add(new String[]{"Estado Civil", meusDado.estadoCivil});
        dados.add(new String[]{"Nome do Pai", meusDado.nomePai});
        dados.add(new String[]{"Nome da Mãe", meusDado.nomeMae});
        dados.add(new String[]{"Cor/Raça", meusDado.corRaca});
        dados.add(new String[]{"Profissão", meusDado.profissao});
        dados.add(new String[]{"Local de Trabalho", meusDado.localTrabalho});
        dados.add(new String[]{"Naturalidade", meusDado.naturalidade});
        dados.add(new String[]{"Nacionalidade", meusDado.nacionalidade});
        dados.add(new String[]{"Endereço", meusDado.endereco});
        dados.add(new String[]{"Número da Casa", meusDado.numero});
        dados.add(new String[]{"Birro", meusDado.bairro});
        dados.add(new String[]{"Complemento", meusDado.complemento});
        dados.add(new String[]{"CEP", meusDado.cep});
        dados.add(new String[]{"Cidade", meusDado.cidade});
        dados.add(new String[]{"Estado", meusDado.estado});
        dados.add(new String[]{"Tel. Trabalho", meusDado.telTrabalho});
        dados.add(new String[]{"Tel. Residencial", meusDado.telResidencia});
        dados.add(new String[]{"Tel. Celular", meusDado.telCelular});
        dados.add(new String[]{"Tel. Responsável", meusDado.telResponsavel});
        dados.add(new String[]{"Email", meusDado.email});

        return dados;
    }

}
