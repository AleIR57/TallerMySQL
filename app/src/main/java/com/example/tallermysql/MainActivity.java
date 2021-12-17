package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Empleados(View v){
        Intent empleados = new Intent(this, Empleados.class);
        startActivity(empleados);
    }
    public void Empresas(View v){
        Intent empresas = new Intent(this, Empresas.class);
        startActivity(empresas);
    }

}