package com.devdes.allon.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.devdes.allon.R;

import java.util.ArrayList;

public class ConteudoAulaAdapter extends RecyclerView.Adapter {
    private ArrayList<ConteudoAula.Conteudo> conteudos;
    private Context context;

    public ConteudoAulaAdapter(ArrayList<ConteudoAula.Conteudo> conteudos, Context context) {
        this.conteudos = conteudos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_conteudo_aula, parent, false);

        return new ConteudoAulaHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ConteudoAulaHolder viewHolder = (ConteudoAulaHolder) holder;
        String dataAula = conteudos.get(position).getData() + " | "
        + conteudos.get(position).getInicio() + " às "
        + conteudos.get(position).getTermino();

        viewHolder.data.setText(dataAula);
        viewHolder.conteudo.setText(conteudos.get(position).getConteudo());
    }

    @Override
    public int getItemCount() {
        return conteudos.size();
    }
}
