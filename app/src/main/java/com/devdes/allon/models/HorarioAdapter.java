package com.devdes.allon.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devdes.allon.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        final String strDefault = "N/A";
        Calendar c = Calendar.getInstance();

        if(horario.getPrimeiraAula().equals(""))
            horario.setPrimeiraAula(strDefault);

        if(horario.getSegundaAula().equals(""))
            horario.setSegundaAula(strDefault);

        if(horario.getTerceiraAula().equals(""))
            horario.setTerceiraAula(strDefault);

        if(horario.getQuartaAula().equals(""))
            horario.setQuartaAula(strDefault);

        viewHolder.diaSemana.setText(horario.getNomeDiaSemana());

        viewHolder.primDisc.setText(horario.getPrimeiraAula());
        viewHolder.segDisc.setText(horario.getSegundaAula());
        viewHolder.tercDisc.setText(horario.getTerceiraAula());
        viewHolder.quarDisc.setText(horario.getQuartaAula());

        viewHolder.primHora.setText(hora.getPrimeiraAula());
        viewHolder.segHora.setText(hora.getSegundaAula());
        viewHolder.tercHora.setText(hora.getTerceiraAula());
        viewHolder.quarHora.setText(hora.getQuartaAula());

        c.setTime(new Date());

        Integer diaSemana = c.get(Calendar.DAY_OF_WEEK);

        if(diaSemana.equals(position + 2)){
            viewHolder.badgeHoje.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return horarios.size();
    }
}
