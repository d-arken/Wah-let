package com.ifsp.wah_let;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class FragLancamento extends Fragment {


    public FragLancamento() {
        // Required empty public constructor
    }
    View v;
    EditText editTextData, editTextValor, editTextTipo;
    Calendar myCalendar = Calendar.getInstance();
    Button addButton, removeButton;
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
                dao.setEntry(entry);



            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                dao.setEntry(entry);
            }
        });


        this.v=v;
        return v;
    }

}
