package com.devdes.allon.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devdes.allon.R;

import java.util.ArrayList;

public class InformativoAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Informativo> informativos;

    public InformativoAdapter(ArrayList<Informativo> informativos, Context context) {
        this.informativos = informativos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_informativo, parent, false);

        return new InformativoHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        InformativoHolder viewHolder = (InformativoHolder) holder;

        final Informativo informativo = informativos.get(position);

        Boolean auxBool;

        viewHolder.informativo = informativos.get(position);


        viewHolder.anexoIcon.setVisibility(
                informativo.getAnexos().size() <= 0 ? View.GONE : View.VISIBLE
        );


        if(position > 0){
            auxBool = informativos.get(position - 1).getData()
                    .equals(informativos.get(position).getData());

            viewHolder.dataDivider.setVisibility(auxBool ? View.GONE : View.VISIBLE);
        }

        viewHolder.data.setText(informativo.getData());
        viewHolder.titulo.setText(informativo.getTitulo());
        viewHolder.nomeProfessor.setText(informativo.getProfessor());

    }

    @Override
    public int getItemCount() {
        return informativos.size();
    }
}
