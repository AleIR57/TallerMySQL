package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class EliminarEmpresas extends AppCompatActivity {
    Spinner NombreEmpresas;
    int IDempresa;
    String Nombre;
    TextView prueba;
    ArrayList<String> opciones;
    String resultado = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_empresas);
        prueba = findViewById(R.id.TextViewEliminarEmpresa);
        NombreEmpresas = findViewById(R.id.spinnerEliminarEmpresa);
        opciones = new ArrayList<String>();
        opciones.add("Seleccione una empresa");
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
                        Nombre = result.getString("Nombre");
                        resultado = String.valueOf(IDempresa) + ":" + Nombre + "\n";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                opciones.add(resultado);
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    con.close();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        }).start();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_primero, opciones);
        NombreEmpresas.setAdapter(adapter);

    }

    public void EREMPRESA(View v){
        String seleccion = NombreEmpresas.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion.split(":");
        String idPosicion = posicionSeleccion[0];
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql2 = "DELETE FROM empresa WHERE numeroIDEmpresa='"+idPosicion+"'";
                    Statement stmt2 = con2.createStatement();
                    stmt2.executeUpdate(sql2);
                    opciones.remove(opciones.size()-1);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Empresa eliminada correctamente", Toast.LENGTH_SHORT).show();
                        }
                    });
                    con2.close();

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