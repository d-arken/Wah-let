package com.ifsp.wah_let;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Array;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by msndo on 17-Jun-17.
 */

public class EntrysDAO {
    private float value;
    private Cursor res;
    private SQLiteDatabase db;
    private Contrato.BancoSQL banco;
    private ContentValues values;
    private ArrayList extractArray;
    public EntrysDAO(Context context) {
        banco = new Contrato.BancoSQL(context);
    }

    public float getValue() {
        db = banco.getReadableDatabase();

        res = db.rawQuery("SELECT sum("+Contrato.EntradasBanco.VALOR_COLUNA+") " +
                "from " + Contrato.EntradasBanco.NOME_TABELA,null);
        res.moveToFirst();

        while(res.isAfterLast() ==false) {
            value = res.getFloat(0);
            res.moveToNext();
        }
        db.close();
        return value;
    }

    public boolean setEntry(float value, Date date, String type) {
        values = new ContentValues();
        db = banco.getWritableDatabase();

        values.put(Contrato.EntradasBanco.VALOR_COLUNA, value);
        values.put(Contrato.EntradasBanco.TIPO_COLUNA, type);
        values.put(Contrato.EntradasBanco.DATA_COLUNA, date.toString());
        long result = db.insert(Contrato.EntradasBanco.NOME_TABELA,null,values);
        db.close();
        if (result == -1)
            return false;
        return true;

    }

    public ArrayList<EntrysListView> getExtract(Date from, Date to) {
        extractArray = new ArrayList<EntrysListView>();
        db = banco.getReadableDatabase();

        if (from != null && to != null) {
            res = db.rawQuery("SELECT id,value,type,date FROM " + Contrato.EntradasBanco.NOME_TABELA +
                    "WHERE date BETWEEN " + from + " AND " + to, null);
        } else {
            res = db.rawQuery("SELECT id,value,type,date FROM " + Contrato.EntradasBanco.NOME_TABELA, null);
        }

            res.moveToFirst();
            while (res.isAfterLast() == false) {
                EntrysListView l = new EntrysListView();


             String s = (res.getString(3));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date d = new Date();
                try {
                    d = dateFormat.parse(s);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                l.setDate(d);

                l.setType(res.getString(2));
                l.setValue(res.getFloat(1));
                l.setId(res.getInt(0));

                extractArray.add(l);
                res.moveToNext();
            }
            db.close();
            return extractArray;
        }

    public boolean removeEntry(int id) {
        String where = Contrato.EntradasBanco._ID + "=" + id;
        db = banco.getReadableDatabase();
        int del = db.delete(Contrato.EntradasBanco.NOME_TABELA, where, null);
        db.close();
        if(del!=-1)
            return true;
        return false;

    }



}
