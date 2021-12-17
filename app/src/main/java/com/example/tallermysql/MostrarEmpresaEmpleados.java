package com.example.tallermysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MostrarEmpresaEmpleados extends AppCompatActivity {
    Spinner NombreEmpresas;
    int IDempresa;
    TextView prueba;
    TextView prueba2;
    String resultado = "";
    String resultado2 = "";
    String Nombre;
    EditText id;
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
    ArrayList<String> opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_empresa_empleados);
        prueba = findViewById(R.id.TextViewMostrarEmpresas);
        NombreEmpresas = findViewById(R.id.spinnerMostrarEmpleadosEmpresa);
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

    public void mostrarDatosEmpresa2(View v){
        resultado2 = "";
        prueba2 = findViewById(R.id.TextViewMostrarEmpleados);
        String seleccion = NombreEmpresas.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion.split(":");
        String idPosicion = posicionSeleccion[0];
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql = "SELECT * FROM empleado WHERE numeroIDEmpresa='"+idPosicion+"'";
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

                        resultado2 += String.valueOf(IDempleado) + ". " + Nombres + " " + Apellidos + " " + TipoID + " " + Telefono + " " + Direccion+ "\n"
                                + Correo + " " + NumeroIDEMPRESA + " " + Cargo + " " + Salario + "\n" + "\n";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                prueba2.setText(resultado2);
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