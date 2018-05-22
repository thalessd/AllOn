package com.devdes.allon.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devdes.allon.R;

import java.util.ArrayList;

public class HorarioAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Horario> horarios;

    public HorarioAdapter(ArrayList<Horario> horarios, Context context) {
        this.horarios = horarios;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_horario, parent, false);

        return new HorarioHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HorarioHolder viewHolder = (HorarioHolder) holder;

        final Horario horario = horarios.get(position);
        final Horario.Hora hora = horario.getHora();

        viewHolder.diaSemana.setText(horario.getNomeDiaSemana());

        viewHolder.primDisc.setText(horario.getPrimeiraAula());
        viewHolder.segDisc.setText(horario.getSegundaAula());
        viewHolder.tercDisc.setText(horario.getTerceiraAula());
        viewHolder.quarDisc.setText(horario.getQuartaAula());

        viewHolder.primHora.setText(hora.getPrimeiraAula());
        viewHolder.segHora.setText(hora.getSegundaAula());
        viewHolder.tercHora.setText(hora.getTerceiraAula());
        viewHolder.quarHora.setText(hora.getQuartaAula());

    }

    @Override
    public int getItemCount() {
        return horarios.size();
    }
}
