package com.ifsp.wah_let;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Array;
import java.text.ParseException;
import java.util.Calendar;
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

    public boolean setEntry(EntrysListView entry) {
        values = new ContentValues();
        db = banco.getWritableDatabase();
        Calendar c = Calendar.getInstance();
        c.setTime(entry.getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        values.put(Contrato.EntradasBanco.VALOR_COLUNA, entry.getValue());
        values.put(Contrato.EntradasBanco.TIPO_COLUNA, entry.getType());
        values.put(Contrato.EntradasBanco.DATA_COLUNA, sdf.format(c.getTime()));
        long result = db.insert(Contrato.EntradasBanco.NOME_TABELA,null,values);
        db.close();
        if (result == -1)
            return false;
        return true;

    }

    public ArrayList<EntrysListView> getExtract(int type, String month, String year) {
        extractArray = new ArrayList<EntrysListView>();
        db = banco.getReadableDatabase();
        //type = 0 mensal, 1 anual
        if (type==0) {
            res = db.rawQuery("SELECT id,value,type,date FROM " + Contrato.EntradasBanco.NOME_TABELA + " WHERE strftime('%m', date) IN ('"+month+"') AND strftime('%Y', date) IN ('"+year+"')",null);
        } else if(type ==1) {
            res = db.rawQuery("SELECT id,value,type,date FROM " + Contrato.EntradasBanco.NOME_TABELA + " WHERE strftime('%Y', date) IN ('"+year+"')", null);
        }else{
            res = db.rawQuery("SELECT id,value,type,date FROM " + Contrato.EntradasBanco.NOME_TABELA ,null);
        }

            res.moveToFirst();
            while (res.isAfterLast() == false) {
                EntrysListView l = new EntrysListView();
                String s = (res.getString(3));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    l.setDate(dateFormat.parse(s));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


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

    public String detailEntry(int id) {
        String value="";
        db = banco.getReadableDatabase();

        res = db.rawQuery("SELECT "+Contrato.EntradasBanco.TIPO_COLUNA +
                " from " + Contrato.EntradasBanco.NOME_TABELA
                + " WHERE "+Contrato.EntradasBanco._ID +"="+id,null);
        res.moveToFirst();

        while(res.isAfterLast() ==false) {
            value = res.getString(0);
            res.moveToNext();
        }
        db.close();
        return value;

    }




}
