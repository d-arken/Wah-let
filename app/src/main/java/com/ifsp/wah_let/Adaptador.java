package com.ifsp.wah_let;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<EntrysListView> {
    private ArrayList<EntrysListView> entrysArray;
    private Context context;

    public Adaptador(Context context, int resource, ArrayList<EntrysListView> entrysArray) {
        super(context, resource, entrysArray);
        this.context = context;
        this.entrysArray = entrysArray;
    }

    private static class ViewHolder {
        TextView value;
        TextView type;
        TextView date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.listview, parent, false);
            holder = new ViewHolder();
            holder.value = (TextView) convertView.findViewById(R.id.value);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        EntrysListView lv = entrysArray.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dt;
        try {
            dt = String.valueOf(sdf.format(lv.getDate()));
        }catch (Exception e){
            dt = "Nulo";
        }
        holder.date.setText(dt);
        holder.value.setText(String.valueOf(lv.getValue()));
        holder.type.setText(lv.getType());
        return convertView;
    }
}

