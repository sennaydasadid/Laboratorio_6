package com.example.carteraclientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.carteraclientes.BaseDatos.DatosOpenHelper;
import com.example.carteraclientes.BaseDatos.DataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ActMain extends AppCompatActivity {
    private ListView lstDatos;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton mostrar = (FloatingActionButton) findViewById(R.id.mostrar);
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ActMain.this, ActMostrarCliente.class);
                startActivityForResult(it,0);
            }
        });

        actualizar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ActMain.this, ActNuevoCliente.class);
                //startActivity(it);
                startActivityForResult(it,0);
            }
        });

        actualizar();
    }
    private void actualizar(){

        lstDatos = (ListView) findViewById(R.id.lstDatos);
        //clientes = new ArrayList<String>();

        try {
            DatosOpenHelper dbHelper = new DatosOpenHelper(getApplicationContext());
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //StringBuilder sql = new StringBuilder();
            //sql.append("SELECT * FROM CLIENTES");

            String[] projection = {
                    DataBase.DATOS.COLUMN_NOMBRE,
                    DataBase.DATOS.COLUMN_TELEFONO
            };

            String selection = null;
            String[] selectionArgs = null;

            String sortOrder = DataBase.DATOS.COLUMN_TELEFONO + " DESC";

            Cursor cursor = db.query(
                    DataBase.DATOS.TABLE_NAME,
                    projection,
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );
            //Cursor cursor = db.rawQuery(sql.toString(), null);

            if(cursor.getCount() >= 0) {
                clientes = new ArrayList<>();
                while (cursor.moveToNext()) {
                    String sNombre = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.DATOS.COLUMN_NOMBRE));
                    String sTelefono = cursor.getString(cursor.getColumnIndex(DataBase.DATOS.COLUMN_TELEFONO));
                    clientes.add(sNombre + ": " + sTelefono);
                }
                cursor.close();
            }
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
            lstDatos.setAdapter(adaptador);


            /*datosOpenHelper = new DatosOpenHelper(this);
            conexion = datosOpenHelper.getWritableDatabase();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM CLIENTES");
            String sNombre;
            String sTelefono;
            Cursor resultado = conexion.rawQuery(sql.toString(), null);
            if(resultado.getCount() > 0){
                resultado.moveToFirst();
                do {
                    sNombre = resultado.getString(resultado.getColumnIndex("NOMBRE"));
                    sTelefono = resultado.getString(resultado.getColumnIndex("TELEFONO"));
                    clientes.add(sNombre + ": "+ sTelefono);
                } while (resultado.moveToNext());
            }
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
            lstDatos.setAdapter(adaptador);*/
        }catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        actualizar();
        //super.onActivityResult(requestCode, resultCode, data);
    }




}