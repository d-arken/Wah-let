package com.ifsp.wah_let;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.support.v7.app.AlertDialog.*;


public class FragLancamento extends Fragment {


    public FragLancamento() {
        // Required empty public constructor
    }
    View v;
    EditText editTextData, editTextValor, editTextTipo;
    Calendar myCalendar = Calendar.getInstance();
    Button addButton, removeButton;
    boolean isOk;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    private void updateLabel() {
        editTextData =(EditText) v.findViewById(R.id.editTextData); ;

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTextData.setText(sdf.format(myCalendar.getTime()));
    }

    //Validacoes
    public boolean isNullOrEmpty(String string){
        return TextUtils.isEmpty(string);
    }
    public AlertDialog makeEmptyAlert(String field) {
        AlertDialog alert;
        Builder builder = new Builder(v.getContext());
        builder.setMessage("Campo "+field+"! não pode ser vazio.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        alert = builder.create();
        isOk=false;
        return alert;
    }
    public AlertDialog makeSucessAlert(String field) {
        AlertDialog alert;
        Builder builder = new Builder(v.getContext());
        builder.setMessage("Sucesso! O registro de R$"+field+" está feito!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        alert = builder.create();
        return alert;
    }
    public AlertDialog makeErrorAlert() {
        AlertDialog alert;
        Builder builder = new Builder(v.getContext());
        builder.setMessage("Falha! O registro não pode ser feito!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        alert = builder.create();
        return alert;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v  = inflater.inflate(R.layout.frag_lancamento, container, false);
        editTextData =(EditText) v.findViewById(R.id.editTextData);
        addButton = (Button) v.findViewById(R.id.btnAddSaldo);
        removeButton = (Button) v.findViewById(R.id.btnRemSaldo);
        editTextValor = (EditText)v.findViewById(R.id.editTextValor);
        editTextTipo = (EditText) v.findViewById(R.id.editTextTipo);
        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isOk=true;

                if(isNullOrEmpty(editTextTipo.getText().toString()))
                    makeEmptyAlert("descrição").show();
                if(isNullOrEmpty(editTextData.getText().toString()))
                    makeEmptyAlert("data").show();
                if(isNullOrEmpty(editTextValor.getText().toString()))
                    makeEmptyAlert("valor").show();

                if(isOk) {
                    EntrysListView entry = new EntrysListView();
                    entry.setType(editTextTipo.getText().toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date d = sdf.parse(editTextData.getText().toString());
                        entry.setDate(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    entry.setValue(-Float.valueOf(editTextValor.getText().toString()));

                    EntrysDAO dao = new EntrysDAO(getContext());
                    if(dao.setEntry(entry))
                        makeSucessAlert("-"+editTextValor.getText().toString()).show();
                    else
                        makeErrorAlert().show();
                }

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isOk=true;

                if(isNullOrEmpty(editTextTipo.getText().toString()))
                    makeEmptyAlert("descrição").show();
                if(isNullOrEmpty(editTextData.getText().toString()))
                    makeEmptyAlert("data").show();
                if(isNullOrEmpty(editTextValor.getText().toString()))
                    makeEmptyAlert("valor").show();

                if(isOk) {
                    EntrysListView entry = new EntrysListView();
                    entry.setType(editTextTipo.getText().toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date d = sdf.parse(editTextData.getText().toString());
                        entry.setDate(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    entry.setValue(Float.valueOf(editTextValor.getText().toString()));

                    EntrysDAO dao = new EntrysDAO(getContext());
                    if(dao.setEntry(entry))
                        makeSucessAlert("+"+editTextValor.getText().toString()).show();
                    else
                        makeErrorAlert().show();
                }

            }
        });


        this.v=v;

        return v;
    }

}
