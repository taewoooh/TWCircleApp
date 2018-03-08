package com.example.lg.twcircleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {



                    case R.id.ic_search:
                        Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();
                       Intent intent2 = new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;

                    case R.id.ic_message:
                        Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(MainActivity.this,MessageActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.ic_profile:
                        Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        Intent intent4 = new Intent(MainActivity.this,ProfileActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0,0);
                        break;

                }
            }
        });


    }

}
