package com.devdes.allon.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devdes.allon.R;

public class HorarioHolder extends RecyclerView.ViewHolder {

    final TextView diaSemana;

    final TextView primDisc;
    final TextView segDisc;
    final TextView tercDisc;
    final TextView quarDisc;

    final TextView primHora;
    final TextView segHora;
    final TextView tercHora;
    final TextView quarHora;

    final LinearLayout badgeHoje;


    public HorarioHolder(View itemView) {
        super(itemView);

        diaSemana = itemView.findViewById(R.id.horarioDiaSemana);

        primDisc = itemView.findViewById(R.id.horarioPrimDisc);
        segDisc = itemView.findViewById(R.id.horarioSegDisc);
        tercDisc = itemView.findViewById(R.id.horarioTercDisc);
        quarDisc = itemView.findViewById(R.id.horarioQuarDisc);

        primHora = itemView.findViewById(R.id.horarioPrimHora);
        segHora = itemView.findViewById(R.id.horarioSegHora);
        tercHora = itemView.findViewById(R.id.horarioTercHora);
        quarHora = itemView.findViewById(R.id.horarioQuarHora);

        badgeHoje = itemView.findViewById(R.id.horarioBadgeHoje);
    }
}
