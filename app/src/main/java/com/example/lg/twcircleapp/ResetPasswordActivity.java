package com.example.lg.twcircleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class ResetPasswordActivity extends AppCompatActivity {

    ImageView backimage,delete;
    MaterialEditText editid;
    String id;
    Button loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");







        backimage =(ImageView)  findViewById(R.id.backimage);
        delete = (ImageView) findViewById(R.id.delete);
        editid = (MaterialEditText) findViewById(R.id.editid);
        loginbtn = (Button)  findViewById(R.id.loginbtn);

        editid.setText(id);
        editid.setSelection(editid.getText().length()); //포커스를 editid 끝으로 둔다.

        if (editid.getText().length()>0){

            delete.setVisibility(View.VISIBLE);
        }

        loginbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    loginbtn.setAlpha(0.5f);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    loginbtn.setAlpha(1.0f);

                }
                return false;
            }
        });


        editid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (s.length() > 0){

                        delete.setVisibility(View.VISIBLE);
                    }else if (s.length() == 0){
                        delete.setVisibility(View.INVISIBLE);
                    }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editid.setText(null);

            }
        });

        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
