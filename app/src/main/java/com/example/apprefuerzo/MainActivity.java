package com.example.apprefuerzo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apprefuerzo.vistas.LoginActivity;
import com.example.apprefuerzo.vistas.perfil.PerfilActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout= findViewById(R.id.tablayout);
        viewPager= findViewById(R.id.viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.perfil:
                startActivity(new Intent(this, PerfilActivity.class));
                //Toast.makeText(this, "perfil", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.notificacion:
                Toast.makeText(this, "Notificacion", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.configuracion:
                Toast.makeText(this, "Configuracion", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.salir:
                cerrarSesion();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}