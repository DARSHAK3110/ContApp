package com.example.contapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class LVAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private List<Contact> contactList;

    public LVAdapter(@NonNull Context context, List<Contact> contactList) {
        super(context, R.layout.raw, contactList);
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.raw, parent, false);
        TextView tvChar = (TextView) convertView.findViewById(R.id.tvchar);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvname);
        TextView tvMail = (TextView) convertView.findViewById(R.id.tvmail);
        tvChar.setText(contactList.get(position).getName().substring(0,1).toUpperCase());
        tvName.setText(contactList.get(position).getName());
        tvMail.setText(contactList.get(position).getEmail());
        return convertView;
    }
}
