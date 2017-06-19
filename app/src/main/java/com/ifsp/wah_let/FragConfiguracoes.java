package com.ifsp.wah_let;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragConfiguracoes extends Fragment {
    Button save;
    public SharedPreferences sharedPref;
    public SharedPreferences.Editor editor;
    View v;
    public FragConfiguracoes() {
        // Required empty public constructor
    }
    public AlertDialog makeOkAlert(String str_alert) {
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String nome="";

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.frag_configuracoes, container, false);
        final EditText txtNome = (EditText)v.findViewById(R.id.txtName);
        save = (Button)v.findViewById(R.id.btn_salvar);
        sharedPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor = sharedPref.edit();
                editor.putString("nome", txtNome.getText().toString());
                makeOkAlert("Salvo com sucesso!").show();

            }
        });

        return v;
    }

}