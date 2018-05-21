package com.devdes.allon.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.devdes.allon.R;
import com.devdes.allon.models.Preferencias;

public class PanelActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setCheckedItem(R.id.nav_inicio);

        this.exibeTela(R.id.nav_inicio);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        this.exibeTela(item.getItemId());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void exibeTela(Integer id){

        Toolbar toolbar = findViewById(R.id.toolbar);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        Intent intent = getIntent();

        HomeScreenFragment homeScreenFragment = new HomeScreenFragment();
        HorarioAulaFragment horarioAulaFragment = new HorarioAulaFragment();
        NotasFragment notasFragment = new NotasFragment();
        ConteudoAulaFragment conteudoAulaFragment = new ConteudoAulaFragment();

        bundle.putString(
                getString(R.string.const_login_token),
                intent.getStringExtra(getString(R.string.const_login_token))
        );

        bundle.putInt(
                getString(R.string.const_login_identificador),
                intent.getIntExtra(getString(R.string.const_login_identificador), 0)
        );


        homeScreenFragment.setArguments(bundle);
        horarioAulaFragment.setArguments(bundle);
        notasFragment.setArguments(bundle);
        conteudoAulaFragment.setArguments(bundle);


        if (id == R.id.nav_inicio) {
            toolbar.setTitle("Aluno Online");
            ft.replace(R.id.inputFragment, homeScreenFragment);
        } else if (id == R.id.nav_aulas) {
            toolbar.setTitle("Horário de Aulas");
            ft.replace(R.id.inputFragment, horarioAulaFragment);
        } else if (id == R.id.nav_notas) {
            toolbar.setTitle("Notas");
            ft.replace(R.id.inputFragment, notasFragment);
        } else if (id == R.id.nav_conteudo_aulas) {
            toolbar.setTitle("Conteudo das Aulas");
            ft.replace(R.id.inputFragment, conteudoAulaFragment);
        } else if (id == R.id.nav_ajuda) {
            startActivity(new Intent(this, AjudaActivity.class));
        } else if (id == R.id.nav_sair) {
            Preferencias preferencias = new Preferencias();
            preferencias.limpaLoginResp(this);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        ft.addToBackStack(null);

        ft.commit();

    }
}
