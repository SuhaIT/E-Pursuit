package com.jerucloud.epursuit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class home_recreater extends AppCompatActivity {
    static FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_recreater);
        frame = findViewById(R.id.frrame);
        ImageView imageView8 = findViewById(R.id.imageView8);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home_recreater.this,all.class);
                startActivity(i);
            }
        });
        setFragment(new homerecfrag());
        ImageView imageViiew7=findViewById(R.id.imageViiew7);
        imageViiew7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home_recreater.this,profile.class);
                i.putExtra("rec",1);
                startActivity(i);

            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_narvigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        setFragment(new homerecfrag());
                        break;
                    case R.id.add:
                        Intent i = new Intent(home_recreater.this,createjabad.class);
                        startActivity(i);
                        break;
                    case R.id.chat:
                        setFragment(new chat());
                        break;
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment frag) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frame.getId(),frag);
        fragmentTransaction.commit();
    }

}