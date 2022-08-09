package com.jerucloud.epursuit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class home extends AppCompatActivity {
   static FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frame = findViewById(R.id.frame);

        if (getIntent().hasExtra("frag")){
    if (getIntent().getStringExtra("frag").equals("1")){
        setFragment(new chat());
    }
}else{
setFragment(new homefrag());
}
        ImageView imageView7=findViewById(R.id.imageView7);
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(home.this,profile.class);

                startActivity(i);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        setFragment(new homefrag());
break;
                    case R.id.reminders:
                        setFragment(new reminders());
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