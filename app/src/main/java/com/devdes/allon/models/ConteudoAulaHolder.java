package com.devdes.allon.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.devdes.allon.R;

public class ConteudoAulaHolder extends RecyclerView.ViewHolder {
    final TextView data;
    final TextView conteudo;

    public ConteudoAulaHolder(View itemView) {
        super(itemView);
        data = itemView.findViewById(R.id.tvConteudoAulaData);
        conteudo = itemView.findViewById(R.id.tvConteudoAulaConteudo);
    }
}
