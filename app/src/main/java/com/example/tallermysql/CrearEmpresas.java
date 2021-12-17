package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearEmpresas extends AppCompatActivity {
    Spinner tipoEmpresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empresas);
        tipoEmpresa = findViewById(R.id.spinnerTipoEmpresa);
        String [] opciones = {"Microempresa", "Empresa pequeña", "Empresa mediana", "Empresa Grande"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_primero, opciones);

        tipoEmpresa.setAdapter(adapter);
    }
    public void CREMPRESA(View v){

        String seleccion = tipoEmpresa.getSelectedItem().toString();
        EditText nit = findViewById(R.id.EditTextNit);
        EditText nombre = findViewById(R.id.EditTextNombre);
        EditText telefono = findViewById(R.id.EditTextTelefonoEmpresa);
        EditText direccion = findViewById(R.id.EditTextDireccionEmpresa);
        EditText correo = findViewById(R.id.EditTextCorreoEmpresa);

        if(validar()) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("INSERT INTO empresa VALUES(null,'" + nit.getText().toString() + "','" + seleccion + "','" + nombre.getText().toString() + "','" + telefono.getText().toString() + "','" + direccion.getText().toString() + "','" + correo.getText().toString() + "')");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Empresa creada correctamente", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public boolean validar(){
        boolean retorno = true;
        String seleccion = tipoEmpresa.getSelectedItem().toString();
        EditText nit = findViewById(R.id.EditTextNit);
        EditText nombre = findViewById(R.id.EditTextNombre);
        EditText telefono = findViewById(R.id.EditTextTelefonoEmpresa);
        EditText direccion = findViewById(R.id.EditTextDireccionEmpresa);
        EditText correo = findViewById(R.id.EditTextCorreoEmpresa);

        String nombres1 = nombre.getText().toString();
        String nits1 = nit.getText().toString();
        String direccion1 = direccion.getText().toString();
        String correo1 = correo.getText().toString();
        String telefono1 = telefono.getText().toString();

        if(nombres1.isEmpty()){
            nombre.setError("Este campo no puede quedar vacio");
            retorno = false;
        }

        if(nits1.isEmpty()){
            nit.setError("Este campo no puede quedar vacio");
            retorno = false;
        }

        if(direccion1.isEmpty()){
            direccion.setError("Este campo no puede quedar vacio");
            retorno = false;
        }

        if(telefono1.isEmpty()){
            telefono.setError("Este campo no puede quedar vacio");
            retorno = false;
        }


        int resultado = correo1.indexOf("@");
        if(correo1.isEmpty()){
            correo.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        else if(resultado == -1){
            correo.setError("Este campo debe contar con un @");
            retorno = false;
        }



        if(seleccion == "Microempresa" || seleccion == "Empresa pequeña" || seleccion == "Empresa mediana" || seleccion == "Empresa Grande"){

        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Debe seleccionar alguno de los elementos de tipo de empresa", Toast.LENGTH_SHORT).show();
            retorno = false;
        }



        return retorno;
    }

    public void volverEmpresa(View v){
        Intent vEmpresa = new Intent(this, Empresas.class);
        startActivity(vEmpresa);
    }

    public void volverInicio(View v){
        Intent vInicio = new Intent(this, MainActivity.class);
        startActivity(vInicio);
    }


}