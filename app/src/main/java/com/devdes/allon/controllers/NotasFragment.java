package com.devdes.allon.controllers;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devdes.allon.R;

public class NotasFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    private class PegaNotaTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    View view;

    public NotasFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notas, container, false);

        new PegaNotaTask().execute();

        return view;
    }

}
