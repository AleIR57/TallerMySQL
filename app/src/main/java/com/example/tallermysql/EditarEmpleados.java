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

public class EditarEmpleados extends AppCompatActivity {
    Spinner TipoIDEDITAR;
    Spinner NombreEmpresas;
    Spinner CargoEDITAR;
    int IDempresa;
    String Nombre;
    Spinner NombreEmpleados;
    int IDempleado;
    String Nombres;
    String Apellidos;
    int Telefono;
    String Correo;
    String Direccion;
    int NumeroIDEMPRESA;
    int Salario;
    TextView prueba;
    String resultado = "";
    String TipoID1;
    String TipoID2;
    EditText tipoID;
    EditText nombres;
    EditText apellidos;
    EditText telefono;
    EditText direccion;
    EditText correo;
    String numeroIDEmpresa;
    EditText numeroIDEmpresa1;
    String Cargo;
    String cargo;
    EditText cargo1;
    EditText salario;
    ArrayList<String> opciones;
    ArrayList<String> opciones3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleados);
        prueba = findViewById(R.id.TextViewEditarEmpleado);


        TipoIDEDITAR = findViewById(R.id.spinnerTipoIDEDITAR);

        String [] opciones2 = {"Cedula", "Tarjeta Identidad", "Registro Civil", "Extranjero"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item_primero, opciones2);

        TipoIDEDITAR.setAdapter(adapter2);

        CargoEDITAR = findViewById(R.id.spinnerCargoEDITAR);

        String [] opciones4 = {"Jefe", "Supervisor", "Vendedor", "Soporte"};

        ArrayAdapter<String> adapter4 = new ArrayAdapter(this, R.layout.spinner_item_primero, opciones4);

        CargoEDITAR.setAdapter(adapter4);



        NombreEmpleados = findViewById(R.id.spinnerEditarEmpleado);
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
                            Thread.sleep(200);
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




        NombreEmpresas = findViewById(R.id.spinnerIDEmpresaEDITAR);
        opciones3 = new ArrayList<String>();
        opciones3.add("Seleccione una empresa");

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
                                opciones3.add(resultado);
                            }
                        });
                        try {
                            Thread.sleep(2000);
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

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item_primero, opciones3);
        adapter3.notifyDataSetChanged();
        NombreEmpresas.setAdapter(adapter3);


    }

    public void mostrarDatos(View v){
        nombres = findViewById(R.id.EditTextNombresEDITAR);
        apellidos = findViewById(R.id.EditTextApellidosEDITAR);
        telefono = findViewById(R.id.EditTextTelefonoEDITAR);
        direccion = findViewById(R.id.EditTextDireccionEDITAR);
        correo = findViewById(R.id.EditTextCorreoEDITAR);
        cargo = CargoEDITAR.getSelectedItem().toString();
        salario = findViewById(R.id.EditTextSalarioEDITAR);

        nombres.setError(null);
        apellidos.setError(null);
        direccion.setError(null);
        telefono.setError(null);
        correo.setError(null);
        salario.setError(null);

        String seleccion = NombreEmpleados.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion.split(":");
        String idPosicion = posicionSeleccion[0];

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/tallermysql", "root", "123456");
                    String sql2 = "SELECT * FROM empleado where numeroID = '" + idPosicion+"'";
                    Statement stmt2 = con2.createStatement();
                    ResultSet result2 = stmt2.executeQuery(sql2);
                    while(result2.next()){
                        TipoID2 = result2.getString("tipoID");
                        Nombres = result2.getString("Nombres");
                        Apellidos = result2.getString("Apellidos");
                        Telefono = result2.getInt("Telefono");
                        Direccion = result2.getString("Direccion");
                        Correo = result2.getString("Correo");
                        NumeroIDEMPRESA = result2.getInt("numeroIDEmpresa");
                        Cargo= result2.getString("Cargo");
                        Salario = result2.getInt("Salario");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                nombres.setText(Nombres);
                                apellidos.setText(Apellidos);
                                telefono.setText(Telefono + "");
                                direccion.setText(Direccion);
                                correo.setText(Correo);
                                salario.setText(Salario + "");
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

    public void EEREMPLEADO(View v){

        TipoID1 = TipoIDEDITAR.getSelectedItem().toString();
        nombres = findViewById(R.id.EditTextNombresEDITAR);
        apellidos = findViewById(R.id.EditTextApellidosEDITAR);
        telefono = findViewById(R.id.EditTextTelefonoEDITAR);
        direccion = findViewById(R.id.EditTextDireccionEDITAR);
        correo = findViewById(R.id.EditTextCorreoEDITAR);
        numeroIDEmpresa = NombreEmpresas.getSelectedItem().toString();
        String seleccion2 = NombreEmpresas.getSelectedItem().toString();
        String[] posicionSeleccion2 = seleccion2.split(":");
        String idPosicion2 = posicionSeleccion2[0];
        cargo = CargoEDITAR.getSelectedItem().toString();
        salario = findViewById(R.id.EditTextSalarioEDITAR);
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
                        String sql3 = "UPDATE empleado SET tipoID='" + TipoID1 + "',Nombres='" + nombres.getText().toString() + "',Apellidos='" + apellidos.getText().toString() + "',Telefono='" + telefono.getText().toString() + "',Direccion='" + direccion.getText().toString() + "',Correo='" + correo.getText().toString() + "',numeroIDEmpresa='" + idPosicion2 + "',Cargo='" + cargo + "',Salario='" + salario.getText().toString() + "' WHERE numeroID='" + idPosicion + "'";
                        Statement stmt3 = con3.createStatement();
                        stmt3.executeUpdate(sql3);
                        opciones.remove(opciones.size() - 1);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Empleado modificado correctamente", Toast.LENGTH_SHORT).show();
                            }
                        });
                        con3.close();


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
        String seleccion = TipoIDEDITAR.getSelectedItem().toString();
        EditText nombres = findViewById(R.id.EditTextNombresEDITAR);
        EditText apellidos = findViewById(R.id.EditTextApellidosEDITAR);
        EditText telefono = findViewById(R.id.EditTextTelefonoEDITAR);
        EditText direccion = findViewById(R.id.EditTextDireccionEDITAR);
        EditText correo = findViewById(R.id.EditTextCorreoEDITAR);
        String seleccion2 = NombreEmpresas.getSelectedItem().toString();
        String[] posicionSeleccion = seleccion2.split(":");
        String idPosicion = posicionSeleccion[0];
        String cargo = CargoEDITAR.getSelectedItem().toString();
        EditText salario = findViewById(R.id.EditTextSalarioEDITAR);

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