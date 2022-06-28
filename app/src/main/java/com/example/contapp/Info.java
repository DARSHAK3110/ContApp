package com.example.contapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Info extends AppCompatActivity {
    private ImageView imgCall, imgEmail, imgEdit, imgDelete;
    private TextView txtName, txtChar;
    private EditText edtName, edtPhone, edtEmail;
    private Button btnChange;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        txtName = findViewById(R.id.tvname);
        txtChar = findViewById(R.id.tvchar);
        edtName = findViewById(R.id.edtEditContactName);
        edtPhone = findViewById(R.id.edtEditContactNumber);
        edtEmail = findViewById(R.id.edtEditContactEmail);
        btnChange = findViewById(R.id.btnchange);
        imgEmail = findViewById(R.id.ivEmail);
        imgEdit = findViewById(R.id.ivEdit);
        imgDelete = findViewById(R.id.ivDelete);

        edtName.setVisibility(View.GONE);
        edtPhone.setVisibility(View.GONE);
        edtEmail.setVisibility(View.GONE);
        btnChange.setVisibility(View.GONE);
        int position = getIntent().getIntExtra("index", 0);
        edtName.setText(ApplicationClass.contacts.get(position).getName());
        edtEmail.setText(ApplicationClass.contacts.get(position).getEmail());
        edtPhone.setText(ApplicationClass.contacts.get(position).getPhone());
        txtChar.setText(ApplicationClass.contacts.get(position).getName().charAt(0));
        txtName.setText(ApplicationClass.contacts.get(position).getName());

    }
}