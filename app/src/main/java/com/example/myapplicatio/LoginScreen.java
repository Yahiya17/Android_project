package com.example.myapplicatio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class LoginScreen extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    TextView signupRedirect;
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://erp-project-e5be6-default-rtdb.firebaseio.com/");
    boolean passwordVisible;
    public String Auid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.loginButton);
        signupRedirect=findViewById(R.id.signupRedirectText);
        View rootView=findViewById(R.id.linear_layout);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
loginButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Auid=username.getText().toString().trim();
     String pass=password.getText().toString().trim();
     Auid=Auid.toUpperCase();
     if(Auid.isEmpty()||pass.isEmpty())
     {
         Toast.makeText(LoginScreen.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
     }
     else {
         databaseReference.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 if(snapshot.hasChild(Auid))
                 {
                     String getpassword=snapshot.child(Auid).child("PASSWORD").getValue(String.class);
                     if(getpassword.equals(pass)){
                        Intent intent=new Intent(LoginScreen.this,MainActivity.class);
                         intent.putExtra("Auid",Auid);
                        startActivity(intent);
                        finish();
                     }
                     else {
                         Toast.makeText(LoginScreen.this, "wrong password", Toast.LENGTH_SHORT).show();
                     }
                 }
                 else {
                     Toast.makeText(LoginScreen.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
     }
    }
});
signupRedirect.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(LoginScreen.this,Signup.class);
        startActivity(intent);
        finish();
    }
});
        password.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right=2;
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=password.getRight()-password.getCompoundDrawables()[right].getBounds().width())
                    {
                        int selection=password.getSelectionEnd();
                        if(passwordVisible)
                        {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }
                        else {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_remove_red_eye_24,0);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

    }

}