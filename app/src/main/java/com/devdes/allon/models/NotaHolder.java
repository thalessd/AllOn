package com.devdes.allon.models;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devdes.allon.R;
import com.devdes.allon.controllers.BottonInformativoViewFragment;

public class NotaHolder extends RecyclerView.ViewHolder {

    final TextView codigo;
    final TextView materia;
    final TextView notaUm;
    final TextView notaDois;
    final TextView notaTres;
    final TextView mediaAtual;
    final TextView mediaFinal;
    final TextView provaFinal;
    final TextView qtdFalta;
    final TextView faltaPercent;




    public NotaHolder(View itemView) {
        super(itemView);

        codigo = itemView.findViewById(R.id.notaCodigo);
        materia = itemView.findViewById(R.id.notaMateria);
        notaUm = itemView.findViewById(R.id.notaUm);
        notaDois = itemView.findViewById(R.id.notaDois);
        notaTres = itemView.findViewById(R.id.notaTres);
        mediaAtual = itemView.findViewById(R.id.notaMedAt);
        mediaFinal = itemView.findViewById(R.id.notaMediaFinal);
        provaFinal = itemView.findViewById(R.id.notaProvaFim);
        qtdFalta = itemView.findViewById(R.id.notaQtdFalta);
        faltaPercent = itemView.findViewById(R.id.notaFaltaPercent);

    }
}
