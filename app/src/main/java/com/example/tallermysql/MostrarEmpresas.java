package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MostrarEmpresas extends AppCompatActivity {
    int IDempresa;
    String TipoEmpresa;
    String Nombre;
    int Nit;
    int Telefono;
    String Correo;
    String Direccion;

    TextView prueba;
    String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_empresas);
        prueba = findViewById(R.id.TextViewCrearEmpleado);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql = "SELECT * FROM empresa";
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery(sql);
                    while(result.next()){
                        IDempresa = result.getInt("numeroIDEmpresa");
                        TipoEmpresa = result.getString("tipoEmpresa");
                        Nombre = result.getString("Nombre");
                        Telefono = result.getInt("Telefono");
                        Direccion = result.getString("Direccion");
                        Correo = result.getString("Correo");
                        resultado += String.valueOf(IDempresa) + ". " + Nombre + " " + TipoEmpresa + " " + Telefono + " " + Direccion+ "\n"
                                + Correo + "\n" + "\n";
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

    public void volverEmpresa(View v){
        Intent vEmpresa = new Intent(this, Empresas.class);
        startActivity(vEmpresa);
    }

    public void volverInicio(View v){
        Intent vInicio = new Intent(this, MainActivity.class);
        startActivity(vInicio);
    }

}