package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MostrarEmpleados extends AppCompatActivity {
    int IDempleado;
    String TipoID;
    String Nombres;
    String Apellidos;
    int Telefono;
    String Correo;
    String Direccion;
    int NumeroIDEMPRESA;
    String Cargo;
    int Salario;
    TextView prueba;
    String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_empleados);
        prueba = findViewById(R.id.TextViewCrearEmpleado);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql = "SELECT * FROM empleado";
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery(sql);
                    while(result.next()){
                        IDempleado = result.getInt("numeroID");
                        TipoID = result.getString("tipoID");
                        Nombres = result.getString("Nombres");
                        Apellidos = result.getString("Apellidos");
                        Telefono = result.getInt("Telefono");
                        Direccion = result.getString("Direccion");
                        Correo = result.getString("Correo");
                        NumeroIDEMPRESA = result.getInt("numeroIDEmpresa");
                        Cargo= result.getString("Cargo");
                        Salario = result.getInt("Salario");
                        resultado += String.valueOf(IDempleado) + ". " + Nombres + " " + Apellidos + " " + TipoID + " " + Telefono + " " + Direccion+ "\n"
                        + Correo + " " + NumeroIDEMPRESA + " " + Cargo + " " + Salario + "\n" + "\n";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                prueba.setText(resultado);
                            }
                        });

                    }
                    con.close();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        }).start();
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