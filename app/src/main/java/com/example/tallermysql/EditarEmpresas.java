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

public class EditarEmpresas extends AppCompatActivity {
    Spinner NombreEmpleados;
    Spinner TipoEmpresaEDITAR;
    int IDempresa;
    int Nit;
    String TipoEmpresa;
    String Nombre;
    int Telefono;
    String Correo;
    String Direccion;
    TextView prueba;
    EditText id;
    String resultado = "";
    EditText nit;
    EditText nombre;
    EditText tipoEmpresa;
    EditText telefono;
    EditText direccion;
    EditText correo;
    ArrayList<String> opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empresas);
        prueba = findViewById(R.id.TextViewEditarEmpresas);
        TipoEmpresaEDITAR = findViewById(R.id.spinnerTipoEmpresaEDITAR);
        String [] opciones2 = {"Microempresa", "Empresa pequeña", "Empresa mediana", "Empresa Grande"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item_primero, opciones2);

        TipoEmpresaEDITAR.setAdapter(adapter2);


        NombreEmpleados = findViewById(R.id.spinnerEditarEmpresas);
        opciones = new ArrayList<String>();
        opciones.add("Seleccione un empleado");
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
        NombreEmpleados.setAdapter(adapter);

    }

    public void mostrarDatosEmpresa(View v){
        nit = findViewById(R.id.EditTextNitEDITAR);
        nombre = findViewById(R.id.EditTextNombreEDITAR);
        telefono = findViewById(R.id.EditTextTelefonoEmpresaEDITAR);
        direccion = findViewById(R.id.EditTextDireccionEmpresaEDITAR);
        correo = findViewById(R.id.EditTextCorreoEmpresaEDITAR);
        TipoEmpresa = TipoEmpresaEDITAR.getSelectedItem().toString();
        String seleccion = NombreEmpleados.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion.split(":");
        String idPosicion = posicionSeleccion[0];

        nombre.setError(null);
        nit.setError(null);
        direccion.setError(null);
        telefono.setError(null);
        correo.setError(null);


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql2 = "SELECT * FROM empresa where numeroIDEmpresa = '" + idPosicion+"'";
                    Statement stmt2 = con2.createStatement();
                    ResultSet result2 = stmt2.executeQuery(sql2);
                    while(result2.next()){
                        Nit = result2.getInt("Nit");
                        Nombre = result2.getString("Nombre");
                        Telefono = result2.getInt("Telefono");
                        Direccion = result2.getString("Direccion");
                        Correo = result2.getString("Correo");
                        TipoEmpresa = result2.getString("tipoEmpresa");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                nit.setText(Nit + "");
                                nombre.setText(Nombre);
                                telefono.setText(Telefono + "");
                                direccion.setText(Direccion);
                                correo.setText(Correo);
                            }
                        });

                    }
                    con2.close();


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }


        }).start();



    }

    public void EEREMPRESA(View v){
        nit = findViewById(R.id.EditTextNitEDITAR);
        nombre = findViewById(R.id.EditTextNombreEDITAR);
        telefono = findViewById(R.id.EditTextTelefonoEmpresaEDITAR);
        direccion = findViewById(R.id.EditTextDireccionEmpresaEDITAR);
        correo = findViewById(R.id.EditTextCorreoEmpresaEDITAR);
        TipoEmpresa = TipoEmpresaEDITAR.getSelectedItem().toString();
        String seleccion2 = TipoEmpresaEDITAR.getSelectedItem().toString();
        String[] posicionSeleccion2 = seleccion2.split(":");
        String idPosicion2 = posicionSeleccion2[0];
        String seleccion = NombreEmpleados.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion.split(":");
        String idPosicion = posicionSeleccion[0];

        if(validar()) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con3 = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql3 = "UPDATE empresa SET Nit='"+nit.getText().toString()+"',tipoEmpresa='" +idPosicion2+"',Nombre='"+nombre.getText().toString()+"',Telefono='"+telefono.getText().toString()+"',Direccion='" +direccion.getText().toString()+"',Correo='" +correo.getText().toString()+"' WHERE numeroIDEmpresa='"+idPosicion+"'";
                    Statement stmt3 = con3.createStatement();
                    stmt3.executeUpdate(sql3);
                    opciones.remove(opciones.size()-1);
                    con3.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Empresa modificada correctamente", Toast.LENGTH_SHORT).show();
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
        String seleccion = TipoEmpresaEDITAR.getSelectedItem().toString();
        EditText nit = findViewById(R.id.EditTextNitEDITAR);
        EditText nombre = findViewById(R.id.EditTextNombreEDITAR);
        EditText telefono = findViewById(R.id.EditTextTelefonoEmpresaEDITAR);
        EditText direccion = findViewById(R.id.EditTextDireccionEmpresaEDITAR);
        EditText correo = findViewById(R.id.EditTextCorreoEmpresaEDITAR);

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