package com.example.myapplicatio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class synch extends AppCompatActivity {
RecyclerView recyclerView;
String heading[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synch);
        recyclerView=findViewById(R.id.recyclerView);
        heading =new String[]{
          "Subject1","Subject2","Subject3","Subject4","Subject5","Subject6"
        };
    }
}