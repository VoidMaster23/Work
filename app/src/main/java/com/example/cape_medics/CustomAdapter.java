package com.example.cape_medics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
public class CustomAdapter extends BaseAdapter {
    Context context;
    CheckBox logos[];
    LayoutInflater inflter;
    public CustomAdapter(Context applicationContext, CheckBox[] logos) {
        this.context = applicationContext;
        this.logos = logos;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !(convertView instanceof CheckBox)) {
            CheckBox checkBox = new CheckBox(this.context);
            convertView = checkBox;
        }
        ((CheckBox) convertView).setText(null);

        return convertView;
    }
}
