package com.ifsp.wah_let;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragConfiguracoes extends Fragment {


    public FragConfiguracoes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_configuracoes, container, false);
        ListView consultaLv = (ListView) v.findViewById(R.id.frag_listview);
        EntrysDAO dao = new EntrysDAO(v.getContext());
        Adaptador adap = new Adaptador(v.getContext(), R.layout.listview, dao.getExtract(2, 0, 2017));
        consultaLv.setAdapter(adap);
        adap.notifyDataSetChanged();
        return v;
    }

}
