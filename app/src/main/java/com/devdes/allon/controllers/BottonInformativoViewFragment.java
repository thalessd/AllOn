package com.devdes.allon.controllers;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devdes.allon.R;
import com.devdes.allon.models.Informativo;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottonInformativoViewFragment extends Fragment {

    private class ItemDownloadHolder extends RecyclerView.ViewHolder {
        final TextView extName;
        final FloatingActionButton extBtnDownload;
        public String link;

        public ItemDownloadHolder(View itemView) {
            super(itemView);
            extName = itemView.findViewById(R.id.extName);
            extBtnDownload = itemView.findViewById(R.id.extBtnDownload);

            extBtnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(link));
                    startActivity(i);
                }
            });

        }
    }

    private class ItemDownloadAdapter extends RecyclerView.Adapter {

        Context context;
        ArrayList<String> links;

        public ItemDownloadAdapter(ArrayList<String> links, Context context) {
            this.context = context;
            this.links = links;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_download_anexo, parent, false);

            return new ItemDownloadHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            String link = links.get(position);

            ItemDownloadHolder viewHolder = (ItemDownloadHolder) holder;
            viewHolder.link = link;
            viewHolder.extName.setText(link.replaceAll("^.*[.]", ""));
        }

        @Override
        public int getItemCount() {
            return links.size();
        }
    }


    private View view;
    public static Informativo informativo;

    public BottonInformativoViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // informativoViewBg
        view = inflater.inflate(R.layout.fragment_botton_informativo_view, container, false);

        view.findViewById(R.id.informativoViewBg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        TextView infoViewNomeProfessor = view.findViewById(R.id.infoViewNomeProfessor);
        TextView infoViewData = view.findViewById(R.id.infoViewData);
        TextView infoViewTitulo = view.findViewById(R.id.infoViewTitulo);
        TextView infoViewDescricao = view.findViewById(R.id.infoViewDescricao);
        TextView infoViewTxAnexo = view.findViewById(R.id.infoViewTxAnexo);
        RecyclerView rvListaArquivo = view.findViewById(R.id.rvListaArquivo);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(
                view.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );

        infoViewNomeProfessor.setText(informativo.getProfessor());
        infoViewData.setText(informativo.getData());
        infoViewTitulo.setText(informativo.getTitulo());
        infoViewDescricao.setText(informativo.getDescricao());

        if(informativo.getAnexos().size() > 0) {
            rvListaArquivo.setAdapter(new ItemDownloadAdapter(informativo.getAnexos(), view.getContext()));
            rvListaArquivo.setLayoutManager(layout);
        }else {
            infoViewTxAnexo.setText("Sem Anexos");
        }



        return view;
    }

}
