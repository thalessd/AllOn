package com.devdes.allon.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devdes.allon.R;

import java.util.ArrayList;

public class NotaAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Nota> notas;

    public NotaAdapter(ArrayList<Nota> notas, Context context) {
        this.notas = notas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_nota, parent, false);

        return new NotaHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NotaHolder viewHolder = (NotaHolder) holder;

        final Nota nota = notas.get(position);
        String ph = "N/A"; // Placeholder

        Integer codigo = nota.getCodigo();
        Double notaUm = nota.getPrimeiraNota();
        Double notaDois = nota.getSegundaNota();
        Double notaTres = nota.getTerceiraNota();
        Double mediaAtual = nota.getMediaAtual();
        Double provaFinal = nota.getProvaFinalNota();
        Double mediaFinal = nota.getMedia();
        Integer qtdFalta = nota.getQuantidadeFalta();
        Integer faltaPercent = nota.getFaltaPercentual();

        viewHolder.materia.setText(nota.getDisciplina());
        viewHolder.codigo.setText(codigo != null ? codigo.toString() : ph);

        viewHolder.notaUm.setText(notaUm != null ? notaUm.toString() : ph);
        viewHolder.notaDois.setText(notaDois != null ? notaDois.toString() : ph);
        viewHolder.notaTres.setText(notaTres != null ? notaTres.toString() : ph);
        viewHolder.mediaAtual.setText(mediaAtual != null ? mediaAtual.toString() : ph);
        viewHolder.provaFinal.setText(provaFinal != null ? provaFinal.toString() : ph);
        viewHolder.mediaFinal.setText(mediaFinal != null ? mediaFinal.toString() : ph);
        viewHolder.qtdFalta.setText(qtdFalta != null ? qtdFalta.toString() : ph);
        viewHolder.faltaPercent.setText(faltaPercent != null ? faltaPercent.toString() + "%" : ph);

    }

    @Override
    public int getItemCount() {
        return notas.size();
    }
}
