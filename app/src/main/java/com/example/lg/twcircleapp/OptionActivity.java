package com.example.lg.twcircleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        back = (ImageView)findViewById(R.id.back);

        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.back :
                finish();
                overridePendingTransition(0,0);
                Toast.makeText(getApplicationContext(),"테스트",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,0);
    }
}
