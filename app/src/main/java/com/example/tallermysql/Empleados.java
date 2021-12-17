package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Empleados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);
    }
    public void MEmpleados(View v){
        Intent mempleados = new Intent(this, MostrarEmpleados.class);
        startActivity(mempleados);
    }
    public void CEmpleados(View v){
        Intent cempleados = new Intent(this, CrearEmpleados.class);
        startActivity(cempleados);
    }
    public void EEmpleados(View v){
        Intent eempleados = new Intent(this, EditarEmpleados.class);
        startActivity(eempleados);
    }
    public void ELEmpleados(View v){
        Intent elempleados = new Intent(this, EliminarEmpleados.class);
        startActivity(elempleados);
    }
    public void volverInicio(View v){
        Intent vInicio = new Intent(this, MainActivity.class);
        startActivity(vInicio);
    }
}