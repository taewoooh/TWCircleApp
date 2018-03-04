package com.example.lg.twcircleapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DialogActivity extends Activity implements View.OnClickListener,View.OnTouchListener {

    private Button mConfirm, mCancel;
    TextView checkbtn, resetbtn;
    RelativeLayout relativelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        Display display = ((WindowManager) getSystemService(DialogActivity.this.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 0.8); //Display 사이즈의 가로

        int height = (int) (display.getHeight() * 0.3);  //Display 사이즈의 세로

        getWindow().getAttributes().width = width;

        getWindow().getAttributes().height = height;

        checkbtn = findViewById(R.id.checkbtn);
        resetbtn = findViewById(R.id.resetbtn);
        relativelayout = findViewById(R.id.relativelayout);

        relativelayout.setOnTouchListener(this);
        checkbtn.setOnClickListener(this);
        resetbtn.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.checkbtn:

                this.finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.resetbtn:
                this.finish();
                Intent intent = new Intent(this, ResetPasswordActivity.class);
                startActivity(intent);
                break;


        }

    }


}



