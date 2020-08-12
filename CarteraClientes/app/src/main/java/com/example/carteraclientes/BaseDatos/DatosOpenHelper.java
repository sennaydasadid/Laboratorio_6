package com.example.carteraclientes.BaseDatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.carteraclientes.ActMostrarCliente;

import com.example.carteraclientes.BaseDatos.DataBase;
import androidx.annotation.Nullable;

public class DatosOpenHelper extends SQLiteOpenHelper{
    private static final String NOMBRE_BD="DATOS.db";
    private static final int VERSION_BD=1;
    private static final String TABLA_CLIENTE=
            "CREATE TABLE " +DataBase.DATOS.TABLE_NAME + " (" +
                    DataBase.DATOS.COLUMN_NOMBRE + " TEXT," +
                    DataBase.DATOS.COLUMN_DIRECCION + " TEXT," +
                    DataBase.DATOS.COLUMN_EMAIL + " TEXT," +
                    DataBase.DATOS.COLUMN_TELEFONO + " TEXT)";
    //"CREATE TABLE CLIENTES(NOMBRE TEXT, DIRECCION TEXT, EMAIL TEXT, TELEFONO TEXT)";

    private static final String DELETE_ENTRIES="DROP TABLE IF EXISTS'"+TABLA_CLIENTE+"'";

    public DatosOpenHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(TABLA_CLIENTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1){
        sqLiteDatabase.execSQL(DELETE_ENTRIES);
        sqLiteDatabase.execSQL(TABLA_CLIENTE);
    }

    public void agregarClientes(String nombre, String direccion, String email, String telefono){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBase.DATOS.COLUMN_NOMBRE, nombre);
        values.put(DataBase.DATOS.COLUMN_DIRECCION, direccion);
        values.put(DataBase.DATOS.COLUMN_EMAIL, email);
        values.put(DataBase.DATOS.COLUMN_TELEFONO, telefono);

        long newRowId = db.insert(DataBase.DATOS.TABLE_NAME, null, values);
        /*
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("INSERT INTO CLIENTES VALUES('"+nombre+"','"+direccion+"','"+email+"','"+telefono+"')");
            bd.close();
        }*/
    }

    public String[] buscar_cliente(String buscar){
        SQLiteDatabase db = getWritableDatabase();
        String datos[]=new String[5];

        Cursor registros=db.rawQuery("select * from CLIENTES where nombre= '"+buscar+"'",null);
        if (registros.moveToFirst()){
            for(int i=0;i<4;i++){
                datos[i]=registros.getString(i);
            }
            datos[4]="Encontrado";
        }else{
            datos[4]="No se encontro a "+buscar;
        }
        return datos;
    }

    public String eliminar_cliente(String cliente){
        String mensaje="";
        SQLiteDatabase db = getWritableDatabase();
        int cantidad = db.delete("CLIENTES","NOMBRE='"+cliente+"'",null);
        if(cantidad!=0){
            mensaje="Eliminado correctamente";
        }else{
            mensaje="Cliente no encontrado";
        }
        return mensaje;
    }

}
