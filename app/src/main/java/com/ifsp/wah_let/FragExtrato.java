package com.ifsp.wah_let;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragExtrato extends Fragment {
    Adaptador adap;
    EditText year;
    ListView consultaLv;
    Spinner spinnerMes,extratoSpinner;
    public FragExtrato() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_extrato, container, false);

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



        EntrysDAO dao = new EntrysDAO(v.getContext());
        adap = new Adaptador(v.getContext(), R.layout.listview, dao.getExtract(2,0, Integer.valueOf(year.getText().toString())));
        consultaLv.setAdapter(adap);
        adap.notifyDataSetChanged();

Log.e("Adap",adap.toString());
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
                EntrysDAO dao = new EntrysDAO(view.getContext());
                spinnerMes.setVisibility(View.INVISIBLE);
                if(position==0) {

                    spinnerMes.setVisibility(View.VISIBLE);

                }
                adap = new Adaptador(view.getContext(), R.layout.listview, dao.getExtract(1,0,Integer.valueOf(year.getText().toString())));
                consultaLv.setAdapter(adap);
                adap.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EntrysDAO dao = new EntrysDAO(view.getContext());
                adap = new Adaptador(view.getContext(), R.layout.listview, dao.getExtract(0,position,Integer.valueOf(year.getText().toString())));
                consultaLv.setAdapter(adap);
                adap.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

}
