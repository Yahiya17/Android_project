package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
EditText username,auid,usn,password;
TextView loginRedirectText;
Button signupButton;
FirebaseDatabase database;
DatabaseReference reference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://erp-project-e5be6-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username=findViewById(R.id.username);
        auid=findViewById(R.id.auid);
        usn=findViewById(R.id.usn);
        password=findViewById(R.id.password);
        signupButton=findViewById(R.id.registerButton);
        loginRedirectText=findViewById(R.id.loginRedirectText);
        View rootView=findViewById(R.id.linear_layout);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=FirebaseDatabase.getInstance();
                String name=username.getText().toString();
                String auid1=auid.getText().toString();
                String usn1=usn.getText().toString();
                String password1=password.getText().toString();
                if(name.isEmpty()||auid1.isEmpty()||usn1.isEmpty()||password1.isEmpty())
                {
                    Toast.makeText(Signup.this, "Fill all the details properly", Toast.LENGTH_SHORT).show();
                }
                else {
                    reference.child("Students").child(auid1).child("NAME").setValue(name);
                    reference.child("Students").child(auid1).child("AUID").setValue(auid1);
                    reference.child("Students").child(auid1).child("USN").setValue(usn1);
                    reference.child("Students").child(auid1).child("PASSWORD").setValue(password1);
                    Toast.makeText(Signup.this, "You have registered Successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(Signup.this,LoginScreen.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this,LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }

}