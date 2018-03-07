package com.example.lg.twcircleapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        Toast.makeText(MainActivity.this,"action add clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.search:
                        Toast.makeText(MainActivity.this,"action edit clicked",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.like:
                        Toast.makeText(MainActivity.this,"action remove clicked",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.myprofile:
                        Toast.makeText(MainActivity.this,"action remove clicked",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }
}
