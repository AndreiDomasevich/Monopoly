package com.example.gardi.monopoly;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by gardi on 14.11.2016.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<Integer> {

    Integer[] objects;

    public CustomSpinnerAdapter(Context context, int textViewResourceId, Integer[] objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position,convertView, parent);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView textView = (TextView) view;

        textView.setBackgroundColor(objects[position]);
        textView.setText("");
        textView.setHeight(40);
        textView.setWidth(40);

        return view;
    }
}
