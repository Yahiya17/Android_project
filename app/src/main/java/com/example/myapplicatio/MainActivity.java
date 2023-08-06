package com.example.myapplicatio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int counter=0;
    private ProgressBar progressBar;
    private Button startProgress;
    TextView username,usn;
    String Auid;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://erp-project-e5be6-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar=findViewById(R.id.topAppbar);
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        username=findViewById(R.id.username);
        usn=findViewById(R.id.usn);

        databaseReference.child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Intent intent=getIntent();
                 Auid=intent.getStringExtra("Auid");
                String USERNAME=snapshot.child(Auid).child("NAME").getValue(String.class);
                String USN=snapshot.child(Auid).child("USN").getValue(String.class);
                //String usernametxt=snapshot.child(auid).child("NAME").getValue(String.class);
                username.setText(USERNAME);
                usn.setText(USN);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        progressBar=findViewById(R.id.progressBar);

prog();

                //progressBar.setProgress(50);  // Set the progress value (between 0 and 100)




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
              if(id==R.id.nav_home) {
                  Intent intent=new Intent(MainActivity.this, My_profile.class);
                  intent.putExtra("Auid",Auid);
                  startActivity(intent);
                  overridePendingTransition(R.anim.from_left, R.anim.from_right);
              }
              else if(id==R.id.nav_message)
              {
                  drawerLayout.closeDrawer(GravityCompat.START);
                  return true;
              }
              else if(id==R.id.nav_synch)
              {
                  Intent intent=new Intent(MainActivity.this,synch.class);
                  startActivity(intent);
                  overridePendingTransition(R.anim.from_left, R.anim.from_right);
              }
              else if(id==R.id.nav_trash)

              {
                  Intent intent=new Intent(MainActivity.this,trash.class);
                  startActivity(intent);
                  overridePendingTransition(R.anim.from_left, R.anim.from_right);
              }
              else if(id==R.id.nav_settings)

              {
                  Intent intent=new Intent(MainActivity.this,settings.class);
                  startActivity(intent);
                  overridePendingTransition(R.anim.from_left, R.anim.from_right);
              }
              else if(id==R.id.nav_login)
              {
                  Intent intent=new Intent(MainActivity.this,login.class);
                  startActivity(intent);
                  overridePendingTransition(R.anim.from_left, R.anim.from_right);
              }
              else if(id==R.id.nav_share)
              {
                  Intent intent=new Intent(MainActivity.this,LoginScreen.class);
                  startActivity(intent);
                  finish();
                  overridePendingTransition(R.anim.from_left, R.anim.from_right);
              }
              else if(id==R.id.nav_rate)
              {
                  Intent intent=new Intent(MainActivity.this,Rate.class);
                  startActivity(intent);
                  overridePendingTransition(R.anim.from_left, R.anim.from_right);
              }
              else
                  return true;
                  return true;
            }
        });

    }
public void prog()
{
    Timer t=new Timer();
    TimerTask tt=new TimerTask() {
        @Override
        public void run() {
            counter+=5;
            progressBar.setProgress(counter);
            if(counter==90)
            {
                t.cancel();;
            }
        }
    };
    t.schedule(tt,0,100);
}

}