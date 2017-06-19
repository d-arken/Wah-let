package com.ifsp.wah_let;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class Contrato {



    public static class EntradasBanco implements BaseColumns {
        public static final String NOME_TABELA = "ENTRY";
        public static final String VALOR_COLUNA = "VALUE";
        public static final String DATA_COLUNA = "DATE";
        public static final String TIPO_COLUNA = "TYPE";
        public static final String _ID = "id";
    }
    public static class BancoSQL extends SQLiteOpenHelper {
        public BancoSQL(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "bancosql.db";
        private String CREATE_TABLE = "CREATE TABLE " + Contrato.EntradasBanco.NOME_TABELA + "("
                + Contrato.EntradasBanco._ID + " INTEGER PRIMARY KEY,"
                + Contrato.EntradasBanco.VALOR_COLUNA + " REAL,"
                + Contrato.EntradasBanco.DATA_COLUNA + " DATE,"
                + Contrato.EntradasBanco.TIPO_COLUNA + " CHAR(50)"
                + ");";

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DELETE_ALL);
            onCreate(db);
        }


        private String DELETE_ALL = "DROP TABLE IF EXISTS " + Contrato.EntradasBanco.NOME_TABELA;


    }

}




