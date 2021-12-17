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

public class EliminarEmpleados extends AppCompatActivity {
    Spinner NombreEmpleados;
    int IDempleado;
    String Nombres;
    String Apellidos;
    TextView prueba;
    ArrayList<String> opciones;
    String resultado = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_empleados);
        prueba = findViewById(R.id.TextViewEliminarEmpleado);
        NombreEmpleados = findViewById(R.id.spinnerEliminarEmpleado);
        opciones = new ArrayList<String>();
        opciones.add("Seleccione un empleado");
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
                        Nombres = result.getString("Nombres");
                        Apellidos = result.getString("Apellidos");
                        resultado = String.valueOf(IDempleado) + ":" + Nombres + " " + Apellidos + "\n";
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
        NombreEmpleados.setAdapter(adapter);

    }


    public void EREMPLEADO(View v){
        String seleccion = NombreEmpleados.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion.split(":");
        String idPosicion = posicionSeleccion[0];
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql2 = "DELETE FROM empleado WHERE numeroID='"+idPosicion+"'";
                    Statement stmt2 = con2.createStatement();
                    stmt2.executeUpdate(sql2);
                    opciones.remove(opciones.size()-1);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Empleado eliminado correctamente", Toast.LENGTH_SHORT).show();
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

    public void volverEmpleado(View v){
        Intent vEmpleado = new Intent(this, Empleados.class);
        startActivity(vEmpleado);
    }

    public void volverInicio(View v){
        Intent vInicio = new Intent(this, MainActivity.class);
        startActivity(vInicio);
    }





}