package com.ifsp.wah_let;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragLancamento extends Fragment {


    public FragLancamento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.frag_lancamento, container, false);
        final EditText editTextData;
        editTextData = (EditText) v.findViewById(R.id.editTextData);
        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker;
                datePicker = (DatePicker) v.findViewById(R.id.datePicker);
                datePicker.setVisibility(View.VISIBLE);
                editTextData.setText(datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear());
            }
        });

        return v;
    }

}
