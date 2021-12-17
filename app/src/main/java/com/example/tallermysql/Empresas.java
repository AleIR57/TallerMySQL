package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Empresas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas);
    }
    public void MEmpresas(View v){
        Intent mempresas = new Intent(this, MostrarEmpresas.class);
        startActivity(mempresas);
    }
    public void CEmpresas(View v){
        Intent cempresas = new Intent(this, CrearEmpresas.class);
        startActivity(cempresas);
    }
    public void EEmpresas(View v){
        Intent eempresas = new Intent(this, EditarEmpresas.class);
        startActivity(eempresas);
    }
    public void ELEmpresas(View v){
        Intent elempresas= new Intent(this, EliminarEmpresas.class);
        startActivity(elempresas);
    }
    public void volverInicio(View v){
        Intent vInicio = new Intent(this, MainActivity.class);
        startActivity(vInicio);
    }
    public void MEmpresasEmpleados(View v){
        Intent mempresasempleados= new Intent(this, MostrarEmpresaEmpleados.class);
        startActivity(mempresasempleados);
    }

}