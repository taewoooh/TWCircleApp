package com.example.lg.twcircleapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class DialogActivity extends Activity {

    private Button mConfirm, mCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        Display display = ((WindowManager) getSystemService(DialogActivity.this.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 0.8); //Display 사이즈의 가로

        int height = (int) (display.getHeight() * 0.3);  //Display 사이즈의 세로

        getWindow().getAttributes().width = width;

        getWindow().getAttributes().height = height;




    }


}



