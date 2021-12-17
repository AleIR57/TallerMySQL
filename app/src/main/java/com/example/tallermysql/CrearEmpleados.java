package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CrearEmpleados extends AppCompatActivity {
    Spinner tipoID;
    Spinner Cargo;
    Spinner NombreEmpresas;
    int IDempresa;
    String Nombre;
    String resultado = "";
    ArrayList<String> opciones2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empleados);
        Cargo = findViewById(R.id.spinnerCargo);
        tipoID = findViewById(R.id.spinnerTipoID);


        String [] opciones = {"Cedula", "Tarjeta Identidad", "Registro Civil", "Extranjero"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_primero, opciones);

        tipoID.setAdapter(adapter);

        String [] opciones3 = {"Jefe", "Supervisor", "Vendedor", "Soporte"};

        ArrayAdapter<String> adapter3 = new ArrayAdapter(this, R.layout.spinner_item_primero, opciones3);

        Cargo.setAdapter(adapter3);

        NombreEmpresas = findViewById(R.id.spinnerIDEmpresa);
        opciones2 = new ArrayList<String>();
        opciones2.add("Seleccione una empresa");

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql2 = "SELECT * FROM empresa";
                    Statement stmt2 = con2.createStatement();
                    ResultSet result2 = stmt2.executeQuery(sql2);
                    while(result2.next()){

                        IDempresa = result2.getInt("numeroIDEmpresa");
                        Nombre = result2.getString("Nombre");
                        resultado = String.valueOf(IDempresa) + ":" + Nombre + "\n";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                opciones2.add(resultado);
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    con2.close();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        }).start();

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item_primero, opciones2);
        adapter2.notifyDataSetChanged();
        NombreEmpresas.setAdapter(adapter2);

    }

    public void doInBackground(View v){

    }



    public void CREMPLEADO(View v){
        String seleccion = tipoID.getSelectedItem().toString();
        EditText nombres = findViewById(R.id.EditTextNombres);
        EditText apellidos = findViewById(R.id.EditTextApellidos);
        EditText telefono = findViewById(R.id.EditTextTelefono);
        EditText direccion = findViewById(R.id.EditTextDireccion);
        EditText correo = findViewById(R.id.EditTextCorreo);
        String seleccion2 = NombreEmpresas.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion2.split(":");
        String idPosicion = posicionSeleccion[0];
        String cargo = Cargo.getSelectedItem().toString();
        EditText salario = findViewById(R.id.EditTextSalario);

            if(validar()){


            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("INSERT INTO empleado VALUES(null,'"+seleccion+"','" +nombres.getText().toString()+"','"+apellidos.getText().toString()+"','"+telefono.getText().toString()+"','" +direccion.getText().toString()+"','" +correo.getText().toString()+"','"+idPosicion+"','"+cargo+"','" +salario.getText().toString()+"')");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Empleado creado correctamente", Toast.LENGTH_SHORT).show();
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
        String seleccion = tipoID.getSelectedItem().toString();
        EditText nombres = findViewById(R.id.EditTextNombres);
        EditText apellidos = findViewById(R.id.EditTextApellidos);
        EditText telefono = findViewById(R.id.EditTextTelefono);
        EditText direccion = findViewById(R.id.EditTextDireccion);
        EditText correo = findViewById(R.id.EditTextCorreo);
        String seleccion2 = NombreEmpresas.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion2.split(":");
        String idPosicion = posicionSeleccion[0];
        String cargo = Cargo.getSelectedItem().toString();
        EditText salario = findViewById(R.id.EditTextSalario);

        String nombres1 = nombres.getText().toString();
        String apellidos1 = apellidos.getText().toString();
        String direccion1 = direccion.getText().toString();
        String correo1 = correo.getText().toString();
        String telefono1 = telefono.getText().toString();
        String salario1 = salario.getText().toString();

        if(nombres1.isEmpty()){
            nombres.setError("Este campo no puede quedar vacio");
            retorno = false;
        }

        if(apellidos1.isEmpty()){
            apellidos.setError("Este campo no puede quedar vacio");
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

        if(salario1.isEmpty()){
            salario.setError("Este campo no puede quedar vacio");
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




        if(seleccion == "Cedula" || seleccion == "Tarjeta Identidad" || seleccion == "Registro Civil" || seleccion == "Extranjero"){

        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Debe seleccionar alguno de los elementos de tipo de identificación", Toast.LENGTH_SHORT).show();
            retorno = false;
        }

        if(cargo == "Jefe" || cargo == "Supervisor" || cargo == "Vendedor" || cargo == "Soporte"){

        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Debe seleccionar alguno de los elementos del cargo", Toast.LENGTH_SHORT).show();
            retorno = false;
        }
        if(NombreEmpresas.getSelectedItem().toString().equals("Seleccione una empresa")){
            Toast.makeText(getApplicationContext(),
                    "Debe seleccionar alguna de las empresas", Toast.LENGTH_SHORT).show();
            retorno = false;
        }


        return retorno;
    }

    public void volverEmpleado(View v){
        Intent vEmpleado = new Intent(this, Empleados.class);
        startActivity(vEmpleado);
    }

    public void volverInicio(View v){
        Intent vInicio = new Intent(this, MainActivity.class);
        startActivity(vInicio);
    }

}