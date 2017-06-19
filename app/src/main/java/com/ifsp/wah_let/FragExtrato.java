package com.ifsp.wah_let;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragExtrato extends Fragment {
    View v;
    ListView consultaLv;
    EditText year;
    Adaptador adap;
    Spinner spinnerMes,extratoSpinner;
    public FragExtrato() {
        // Required empty public constructor
    }

    public String mes(int pos){
        switch (pos){
            case 0:
                return "01";
            case 1:
                return "02";
            case 2:
                return "03";
            case 3:
                return "04";
            case 4:
                return "05";
            case 5:
                return "06";
            case 6:
                return "07";
            case 7:
                return "08";
            case 8:
                return "09";
            case 9:
                return "10";
            case 10:
                return "11";
            case 11:
                return "12";
            default:
                return "0";

        }
    }
    public AlertDialog makeOkAlert(String str_alert) {
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage(str_alert)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        alert = builder.create();
        return alert;
    }
    public AlertDialog makeDeleteAlert(EntrysListView entry) {
        AlertDialog alert;
       final EntrysListView myEntry = entry;
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Deseja remover este registro "+entry.getType()+"?")
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    EntrysDAO dao = new EntrysDAO(v.getContext());

                        if (dao.removeEntry(myEntry.getId())) {
                            makeOkAlert("Registro removido com sucesso!").show();
                        }else{
                            makeOkAlert("Erro ao remover registro").show();
                        }

                    }
                });
        alert = builder.create();
        return alert;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_extrato, container, false);

        consultaLv = (ListView) v.findViewById(R.id.frag_listview);
        year = (EditText)v.findViewById(R.id.editTextExtratoAno);
        spinnerMes = (Spinner) v.findViewById(R.id.spinnerMes);
        extratoSpinner = (Spinner) v.findViewById(R.id.extratoSpinner);

        //Spinner Selecionar Mes ou Ano
        ArrayAdapter<CharSequence> adapterExtratoSpinner = ArrayAdapter.createFromResource(getContext(),R.array.ExtratoArray,android.R.layout.simple_spinner_item);
        adapterExtratoSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        extratoSpinner.setAdapter(adapterExtratoSpinner);
        //Spinner Mês
        ArrayAdapter<CharSequence> adapterSpinnerMes = ArrayAdapter.createFromResource(getContext(),R.array.MesesArray,android.R.layout.simple_spinner_item);
        adapterSpinnerMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(adapterSpinnerMes);
        /*Setar spinner para o mes e ano atual  */
        Date diaHoje = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(diaHoje);
        int numeroMes = calendar.get(Calendar.MONTH);
        int numeroAno = calendar.get(Calendar.YEAR);
        spinnerMes.setSelection(numeroMes);
        year.setText(String.valueOf(numeroAno));

        year.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(extratoSpinner.getSelectedItemPosition()==0){
                    EntrysDAO dao = new EntrysDAO(v.getContext());
                    Adaptador adap = new Adaptador(v.getContext(), R.layout.listview, dao.getExtract(0,mes(spinnerMes.getSelectedItemPosition()),year.getText().toString()));
                    consultaLv.setAdapter(adap);
                    adap.notifyDataSetChanged();
                }else{
                    EntrysDAO dao = new EntrysDAO(v.getContext());
                    Adaptador adap = new Adaptador(v.getContext(), R.layout.listview, dao.getExtract(1,"0",year.getText().toString()));
                    consultaLv.setAdapter(adap);
                    adap.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        EntrysDAO dao = new EntrysDAO(v.getContext());
        adap = new Adaptador(v.getContext(), R.layout.listview, dao.getExtract(2,"0", (year.getText().toString())));
        consultaLv.setAdapter(adap);
        adap.notifyDataSetChanged();


     /*Teste dados
     String dt="";
        for(EntrysListView e : dao.getExtract(2,0, Integer.valueOf(year.getText().toString()))){

            dt+=e.getValue();
            dt+=e.getType();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            try {
                dt += String.valueOf(sdf.format(e.getDate()));
            }catch (Exception f){
                dt += "Nulo";
            }
            Log.e("chave",dt);
        }
        */
        extratoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                spinnerMes.setVisibility(View.INVISIBLE);
                if(position==0) {
                    TextView textViewSelecione = (TextView) v.findViewById(R.id.textViewSelecione);
                    textViewSelecione.setText("Selecione o mês/ano: ");
                    Date diaHoje = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(diaHoje);
                    int numeroMes = calendar.get(Calendar.MONTH);
                    spinnerMes.setSelection(numeroMes);

                    EntrysDAO dao = new EntrysDAO(view.getContext());
                    Adaptador adap = new Adaptador(view.getContext(), R.layout.listview, dao.getExtract(0,mes(spinnerMes.getSelectedItemPosition()),year.getText().toString()));
                    consultaLv.setAdapter(adap);
                    adap.notifyDataSetChanged();
                    spinnerMes.setVisibility(View.VISIBLE);

                }else{
                    TextView textViewSelecione = (TextView) v.findViewById(R.id.textViewSelecione);
                    textViewSelecione.setText("Selecione o ano: ");
                    EntrysDAO dao = new EntrysDAO(view.getContext());
                    Adaptador adap = new Adaptador(view.getContext(), R.layout.listview, dao.getExtract(1,"0",year.getText().toString()));
                    consultaLv.setAdapter(adap);
                    adap.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int c;

                EntrysDAO dao = new EntrysDAO(view.getContext());

                Adaptador adap = new Adaptador(view.getContext(), R.layout.listview, dao.getExtract(0,mes(position),year.getText().toString()));
                consultaLv.setAdapter(adap);
                adap.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        consultaLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EntrysListView entry =(EntrysListView) consultaLv.getItemAtPosition(position);
                makeDeleteAlert(entry).show();
                return false;
            }
        });
        return v;
    }

}
