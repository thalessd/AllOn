package com.devdes.allon.models;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devdes.allon.R;

public class InformativoHolder extends RecyclerView.ViewHolder {
    final TextView data;
    final TextView nomeProfessor;
    final TextView titulo;

    final CardView card;
    final LinearLayout dataDivider;

    public InformativoHolder(View itemView) {
        super(itemView);

        data = itemView.findViewById(R.id.informativoData);
        nomeProfessor = itemView.findViewById(R.id.informativoNomeProfessor);
        titulo = itemView.findViewById(R.id.informativoTitulo);

        card = itemView.findViewById(R.id.informativoCard);
        dataDivider = itemView.findViewById(R.id.informativoDataDivider);

    }
}
