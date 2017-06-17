package com.ifsp.wah_let;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragExtrato extends Fragment {


    public FragExtrato() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_extrato, container, false);

        //Spinner Selecionar Mes ou Ano
        Spinner extratoSpinner = (Spinner) v.findViewById(R.id.extratoSpinner);
        ArrayAdapter<CharSequence> adapterExtratoSpinner = ArrayAdapter.createFromResource(getContext(),R.array.ExtratoArray,android.R.layout.simple_spinner_item);
        adapterExtratoSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        extratoSpinner.setAdapter(adapterExtratoSpinner);
        extratoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner Mês
        Spinner spinnerMes = (Spinner) v.findViewById(R.id.spinnerMes);
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
        EditText editTextExtratoAno = (EditText) v.findViewById(R.id.editTextExtratoAno);
        editTextExtratoAno.setText(String.valueOf(numeroAno));
        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

}
