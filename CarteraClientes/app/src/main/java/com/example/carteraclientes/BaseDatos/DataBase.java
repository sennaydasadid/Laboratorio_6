package com.example.carteraclientes.BaseDatos;

import android.provider.BaseColumns;

public final class DataBase {
    public DataBase(){}
    public static class DATOS implements BaseColumns {
        public static final String TABLE_NAME = "CLIENTES";
        public static final String COLUMN_NOMBRE = "NOMBRE";
        public static final String COLUMN_DIRECCION = "DIRECCION";
        public static final String COLUMN_EMAIL = "EMAIL";
        public static final String COLUMN_TELEFONO = "TELEFONO";
    }
}
