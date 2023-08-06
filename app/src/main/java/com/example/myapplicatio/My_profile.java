package com.example.myapplicatio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class My_profile extends AppCompatActivity {
String Auid;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://erp-project-e5be6-default-rtdb.firebaseio.com/");
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView name1=findViewById(R.id.name1);
        TextView usn1=findViewById(R.id.usn1);
        TextView auid1=findViewById(R.id.auid1);
        databaseReference.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Intent intent=getIntent();
                Auid=intent.getStringExtra("Auid");
                String USERNAME=snapshot.child(Auid).child("NAME").getValue(String.class);
                String USN=snapshot.child(Auid).child("USN").getValue(String.class);
                String AUID=snapshot.child(Auid).child("AUID").getValue(String.class);
                //String usernametxt=snapshot.child(auid).child("NAME").getValue(String.class);
                name1.setText(USERNAME);
                usn1.setText(USN);
                auid1.setText(AUID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}