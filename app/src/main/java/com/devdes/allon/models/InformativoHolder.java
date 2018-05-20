package com.devdes.allon.models;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devdes.allon.R;
import com.devdes.allon.controllers.BottonInformativoViewFragment;
import com.devdes.allon.controllers.NotasFragment;

public class InformativoHolder extends RecyclerView.ViewHolder {
    final TextView data;
    final TextView nomeProfessor;
    final TextView titulo;

    final CardView card;
    final LinearLayout dataDivider;

    public Informativo informativo;

    public Context context;

    private final FragmentManager fm;



    public InformativoHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;

         fm = ((AppCompatActivity) context).getSupportFragmentManager();



        data = itemView.findViewById(R.id.informativoData);
        nomeProfessor = itemView.findViewById(R.id.informativoNomeProfessor);
        titulo = itemView.findViewById(R.id.informativoTitulo);
        card = itemView.findViewById(R.id.informativoCard);
        dataDivider = itemView.findViewById(R.id.informativoDataDivider);


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottonInformativoViewFragment.informativo = informativo;

                FragmentTransaction ft = fm.beginTransaction();

                ft.setCustomAnimations(R.anim.fadein, R.anim.fadeout);

                ft.add(R.id.panelCoordinator, new BottonInformativoViewFragment());

                ft.addToBackStack(null);

                ft.commit();
            }
        });
    }
}
