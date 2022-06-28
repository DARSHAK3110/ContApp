package com.example.contapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Information extends AppCompatActivity {
    private ImageView imgCall, imgEmail, imgEdit, imgDelete;
    private TextView txtName;
    private EditText edtName, edtPhone, edtEmail;
    private Button btnChange;
    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        txtName = findViewById(R.id.tvName);
        edtName = findViewById(R.id.edtEditContactName);
        edtPhone = findViewById(R.id.edtEditContactNumber);
        edtEmail = findViewById(R.id.edtEditContactEmail);
        btnChange = findViewById(R.id.btnchange);
        imgCall = findViewById(R.id.ivCall);
        imgEmail = findViewById(R.id.ivEmail);
        imgEdit = findViewById(R.id.ivEdit);
        imgDelete = findViewById(R.id.ivDelete);
        edtName.setVisibility(View.GONE);
        edtPhone.setVisibility(View.GONE);
        edtEmail.setVisibility(View.GONE);
        btnChange.setVisibility(View.GONE);
        int position = getIntent().getIntExtra("index", 0);
        txtName.setText(ApplicationClass.contacts.get(position).getName().toString().trim().toUpperCase());


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit = !isEdit;
                if (isEdit) {

                    edtName.setVisibility(View.VISIBLE);
                    edtPhone.setVisibility(View.VISIBLE);
                    edtEmail.setVisibility(View.VISIBLE);
                    btnChange.setVisibility(View.VISIBLE);
                    edtName.setText(txtName.getText().toString());
                    edtPhone.setText(ApplicationClass.contacts.get(position).getPhone().toString().trim());
                    edtEmail.setText(ApplicationClass.contacts.get(position).getEmail().toString().trim());

                }
                else{
                    edtName.setVisibility(View.GONE);
                    edtPhone.setVisibility(View.GONE);
                    edtEmail.setVisibility(View.GONE);
                    btnChange.setVisibility(View.GONE);
                }
            }
        });
        imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:" + ApplicationClass.contacts.get(position).getEmail().toString().trim()));
                startActivity(intent);
            }
       });
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + ApplicationClass.contacts.get(position).getPhone().toString().trim()));
                startActivity(intent);
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.Persistence.of(Contact.class).remove(ApplicationClass.contacts.get(position), new AsyncCallback<Long>() {
                    @Override
                    public void handleResponse(Long response) {
                        ApplicationClass.contacts.remove(position);
                        Toast.makeText(Information.this, "Deleted!", Toast.LENGTH_SHORT).show();
                        Information.this.finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Information.this, "Error: "+ fault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            });
        }
        });

    btnChange.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (edtName.getText().toString().trim().isEmpty() || edtPhone.getText().toString().trim().isEmpty() || edtEmail.getText().toString().trim().isEmpty()) {
                Toast.makeText(Information.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                ApplicationClass.contacts.get(position).setName(edtName.getText().toString().trim());
                ApplicationClass.contacts.get(position).setPhone(edtPhone.getText().toString().trim());
                ApplicationClass.contacts.get(position).setEmail(edtEmail.getText().toString().trim());
                Backendless.Persistence.of(Contact.class).save(ApplicationClass.contacts.get(position),new AsyncCallback<Contact>() {
                    @Override
                    public void handleResponse(Contact response) {

                        txtName.setText(ApplicationClass.contacts.get(position).getName().toString().trim().toUpperCase());
                        Toast.makeText(Information.this, "Contact Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(Information.this, "Error: "+ fault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    });
    }


}