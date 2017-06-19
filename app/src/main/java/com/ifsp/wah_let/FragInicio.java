package com.ifsp.wah_let;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragInicio extends Fragment {
    TextView tvTotal,txtUser;

    public FragInicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.frag_inicio, container, false);

        tvTotal = (TextView)v.findViewById(R.id.valorSaldo);
        EntrysDAO dao = new EntrysDAO(v.getContext());
        tvTotal.setText("R$ "+String.valueOf(dao.getValue()));
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());

        return v;
    }

}
