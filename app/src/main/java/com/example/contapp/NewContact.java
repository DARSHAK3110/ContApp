package com.example.contapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

public class NewContact extends AppCompatActivity {
    private EditText edtName,edtEmail,edtNumber;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        edtName = findViewById(R.id.edtNewContactName);
        edtNumber = findViewById(R.id.edtNewContactNumber);
        edtEmail = findViewById(R.id.edtNewContactEmail);
        add = findViewById(R.id.btnadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String number = edtNumber.getText().toString().trim();
                String userId = ApplicationClass.user.getEmail();

                if(name.isEmpty() || email.isEmpty() || number.isEmpty()){
                    Toast.makeText(NewContact.this, "Fill all details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Contact contact = new Contact();
                    contact.setName(name);
                    contact.setEmail(email);
                    contact.setPhone(number);
                    contact.setUserEmail(userId);
                    Backendless.Persistence.save(contact, new BackendlessCallback<Contact>() {
                        @Override
                        public void handleResponse(Contact response) {
                            Toast.makeText(NewContact.this, "Contact Added", Toast.LENGTH_SHORT).show();
                            NewContact.this.finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(NewContact.this, "Error: "+fault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}