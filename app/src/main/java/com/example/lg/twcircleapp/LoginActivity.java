package com.example.lg.twcircleapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialEditText editid, editpassword;
    ImageView delete, delete2, hide;
    RelativeLayout layout, relativeLayout;
    Button loginbtn;
    TextView view;
    InputMethodManager imm;
    boolean aBoolean = true;
    boolean booid, boopass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editid = (MaterialEditText) findViewById(R.id.editid);
        editpassword = (MaterialEditText) findViewById(R.id.editpassword);
        delete = (ImageView) findViewById(R.id.delete);
        hide = (ImageView) findViewById(R.id.hide);
        layout = (RelativeLayout) findViewById(R.id.layout);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        delete2 = (ImageView) findViewById(R.id.delete2);
        view = (TextView) findViewById(R.id.textlogin);
       loginbtn = (Button) findViewById(R.id.loginbtn);




        delete.setOnClickListener(this);
        delete2.setOnClickListener(this);
        hide.setOnClickListener(this);
        layout.setOnClickListener(this);
        loginbtn.setOnClickListener(this);
        view.setOnClickListener(this);


        SpannableString content = new SpannableString("다른방법으로 로그인");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        view.setText(content);


        editid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                String emailid = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";  //이메일 형식 체크
                Pattern p = Pattern.compile(emailid);
                Matcher m = p.matcher(s);
                booid = m.matches();


                if (s.length() > 0) {

                    delete.setVisibility(View.VISIBLE);

                } else if (s.length() == 0) {

                    delete.setVisibility(View.INVISIBLE);

                }
            }
        });
        editpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                String regex = "^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$";// 패스워드 4자리에서 16자리까지 조건식
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(s);
                boopass = m.matches();


                if (s.length() > 0) {

                    delete2.setVisibility(View.VISIBLE);
                } else if (s.length() == 0) {

                    delete2.setVisibility(View.INVISIBLE);
                }

            }
        });


    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.delete) {
            Toast.makeText(getApplicationContext(), "테스트", Toast.LENGTH_SHORT).show();
            editid.setText(null);
        } else if (v.getId() == R.id.hide) {


            if (aBoolean == true) {

                editpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                hide.setBackground(getDrawable(R.drawable.onhide));

                aBoolean = false;


            } else if (aBoolean == false) {
                editpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                hide.setBackground(getDrawable(R.drawable.offhide));
                aBoolean = true;

            }

        } else if (v.getId() == R.id.layout) {
            imm.hideSoftInputFromWindow(layout.getWindowToken(), 0); // 레이아웃 터치시 키보드 내림

        } else if (v.getId() == R.id.delete2) {

            editpassword.setText(null);
        } else if (v.getId() == R.id.loginbtn) {

            if (booid == true && boopass == true) {


            } else {
                new SuperToast("접속 실패", "D81B60", LoginActivity.this);

            }



        } else if (v.getId() == R.id.textlogin) {

            TWBottomSheetDialog bottomSheetDialog = TWBottomSheetDialog.getInstance();
            bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");


        }
    }


}