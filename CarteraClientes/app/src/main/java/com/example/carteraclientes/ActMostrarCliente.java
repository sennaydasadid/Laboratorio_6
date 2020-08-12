package com.example.carteraclientes;

import android.os.Bundle;

import com.example.carteraclientes.BaseDatos.DatosOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActMostrarCliente extends AppCompatActivity {
    EditText Ebuscar;
    Button Bbuscar,Beliminar;

    private TextView Nombre;
    private TextView Direccion;
    private TextView Email;
    private TextView Telefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mostrar_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Nombre = (TextView) findViewById(R.id.nombre);
        Direccion = (TextView) findViewById(R.id.direccion);
        Email = (TextView) findViewById(R.id.email);
        Telefono = (TextView) findViewById(R.id.telefono);
        Ebuscar = (EditText) findViewById(R.id.EBuscar);
        Bbuscar=(Button)findViewById(R.id.BBuscar);
        Beliminar=(Button)findViewById(R.id.BEliminar);
        Bbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatosOpenHelper datosOpenHelper=new DatosOpenHelper(getApplicationContext());
                String buscar=Ebuscar.getText().toString();
                String[] datos;
                datos=datosOpenHelper.buscar_cliente(buscar);
                Nombre.setText(datos[0]);
                Direccion.setText(datos[1]);
                Email.setText(datos[2]);
                Telefono.setText(datos[3]);
                Toast.makeText(getApplicationContext(),datos[4],Toast.LENGTH_SHORT).show();
            }
        });
        Beliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatosOpenHelper datosOpenHelper=new DatosOpenHelper(getApplicationContext());
                String eliminarCliente=Nombre.getText().toString();
                String mensaje= datosOpenHelper.eliminar_cliente(eliminarCliente);
                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_buscar_cliente,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_cancelar:
                //Toast.makeText(this, "Boton Cancelar seleccionado", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}