package com.example.lg.twcircleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class ResetPasswordActivity extends AppCompatActivity {

    ImageView backimage,delete;
    MaterialEditText editid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        backimage =(ImageView)  findViewById(R.id.backimage);
        delete = (ImageView) findViewById(R.id.delete);
        editid = (MaterialEditText) findViewById(R.id.editid);

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
