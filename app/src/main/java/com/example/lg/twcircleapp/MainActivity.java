package com.example.lg.twcircleapp;

import android.icu.text.DateFormat;
import android.icu.text.NumberFormat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView image[] = new ImageView[4];
    int num = 0;
    String rid = null;
    int lid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        image[0] = (ImageView) findViewById(R.id.image1);
        image[1] = (ImageView) findViewById(R.id.image2);
        image[2] = (ImageView) findViewById(R.id.image3);
        image[3] = (ImageView) findViewById(R.id.image4);

        image[0].setImageResource(R.drawable.shome);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contents, Fragment1.newInstance());
        fragmentTransaction.commit();

        image[0].setOnClickListener(this);
        image[1].setOnClickListener(this);
        image[2].setOnClickListener(this);
        image[3].setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {


            case R.id.image1:
                num = 0;
                rid = "shome";
                fragment = Fragment1.newInstance();

                check();

                image[1].setImageResource(R.drawable.check);
                image[2].setImageResource(R.drawable.camera);
                image[3].setImageResource(R.drawable.profile);


                break;
            case R.id.image2:
                num = 1;
                rid = "scheck";
                fragment = Fragment2.newInstance();

                check();

                image[0].setImageResource(R.drawable.home);
                image[2].setImageResource(R.drawable.camera);
                image[3].setImageResource(R.drawable.profile);
                break;
            case R.id.image3:
                num = 2;
                rid = "scamera";
                fragment = Fragment3.newInstance();

                check();

                image[0].setImageResource(R.drawable.home);
                image[1].setImageResource(R.drawable.check);
                image[3].setImageResource(R.drawable.profile);
                break;
            case R.id.image4:
                num = 3;
                rid = "sprofile";
                fragment = Fragment4.newInstance();

                check();

                image[0].setImageResource(R.drawable.home);
                image[1].setImageResource(R.drawable.check);
                image[2].setImageResource(R.drawable.camera);
                break;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contents, fragment);
        transaction.commit();

    }


    public void check() {
        lid = this.getResources().getIdentifier(rid, "drawable", this.getPackageName());

        for (int i = 0; i < 4; i++) {

            if (num == i) {

              /*  Toast.makeText(MainActivity.this, "if  //  " + i, Toast.LENGTH_SHORT).show();*/
                image[i].setImageResource(lid);

            }
        }


    }



}