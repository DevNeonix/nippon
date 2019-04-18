package com.jolverandre.nippon.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jolverandre.nippon.Fragments.ArticulosFragment;
import com.jolverandre.nippon.Fragments.ClientesFragment;
import com.jolverandre.nippon.Fragments.PedidosFragment;
import com.jolverandre.nippon.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding();

    }

    private void binding() {

        toolbar = findViewById(R.id.aMain_toolbar);
        drawer = findViewById(R.id.aMain_drawer);
        nav = findViewById(R.id.aMain_navView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.mArticulos:
                    changeFragment(new ArticulosFragment(),menuItem);

                break;
            case R.id.mClientes:
                changeFragment(new ClientesFragment(),menuItem);

                break;
            case R.id.mPedidos:
                changeFragment(new PedidosFragment(),menuItem);

                break;
        }
        return true;
    }
    public void changeFragment(Fragment fragment,MenuItem menuItem){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();

        menuItem.setChecked(true);
        getSupportActionBar().setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeFragment(new ArticulosFragment(),nav.getMenu().getItem(0));
    }
}
