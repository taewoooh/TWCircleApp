package com.example.lg.twcircleapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialEditText editid, editpassword;
    ImageView delete, delete2, hide;
    RelativeLayout layout, relativeLayout;
    Button loginbtn;
    private FirebaseAuth mAuth;
    LoginButton loginButton;
    SuperToast toast;
    TextView view, warning;
    InputMethodManager imm;
    boolean aBoolean;
    boolean booid, boopass, loggedIn;
    static int value = 0;
    static String test;
    long mNow;
    /*private CallbackManager mCallbackManager;*/
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Log.e("onCreate", "" + value);

        editid = (MaterialEditText) findViewById(R.id.editid);
        editpassword = (MaterialEditText) findViewById(R.id.editpassword);
        delete = (ImageView) findViewById(R.id.delete);
        hide = (ImageView) findViewById(R.id.hide);
        layout = (RelativeLayout) findViewById(R.id.layout);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        delete2 = (ImageView) findViewById(R.id.delete2);
        view = (TextView) findViewById(R.id.textlogin);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        warning = (TextView) findViewById(R.id.warning);


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


        loginbtn.setAlpha(0.5f);
        loginbtn.setClickable(false);
        loginbtn.setEnabled(false);


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

                cheked();
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
                loginbtn.setAlpha(0.5f);
                loginbtn.setClickable(false);
                loginbtn.setEnabled(false);

                String regex = "^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$";// 패스워드 4자리에서 16자리까지 조건식
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(s);
                boopass = m.matches();


                if (s.length() > 0) {

                    delete2.setVisibility(View.VISIBLE);
                } else if (s.length() == 0) {

                    delete2.setVisibility(View.INVISIBLE);
                }
                cheked();
            }
        });


        /*mCallbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.loginbutton);
        loginButton.setReadPermissions("email");
        // If using in a fragment

        // Callback registration
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });*/

    }

  /*  private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()){

                }else {
                    Toast.makeText(LoginActivity.this,"Facebook 아이디 연동 성공 ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }*/


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.delete) {
            Toast.makeText(getApplicationContext(), "테스트", Toast.LENGTH_SHORT).show();
            editid.setText(null);
        } else if (v.getId() == R.id.hide) {

            Toast.makeText(getApplicationContext(), "테스트" + loggedIn, Toast.LENGTH_SHORT).show();
            if (aBoolean == true) {

                editpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                hide.setBackground(getDrawable(R.drawable.onhide));

                editpassword.setSelection(editpassword.getText().length());
                aBoolean = false;


            } else if (aBoolean == false) {
                editpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                editpassword.setSelection(editpassword.getText().length()); // 포커스 editppassword length 위치로 이동시키기.
                hide.setBackground(getDrawable(R.drawable.offhide));
                aBoolean = true;

            }

        } else if (v.getId() == R.id.layout) {
            imm.hideSoftInputFromWindow(layout.getWindowToken(), 0); // 레이아웃 터치시 키보드 내림

        } else if (v.getId() == R.id.delete2) {

            editpassword.setText(null);
        } else if (v.getId() == R.id.loginbtn) {

            TWPreference pre = new TWPreference(LoginActivity.this);

            String userid = editid.getText().toString();
            String userpassword = editpassword.getText().toString();
            String usertime = getTime();

            com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonReponse = new JSONObject(response);
                        value = jsonReponse.getInt("value");

                        Log.e("onResponse", "" + value + "<  >" + test);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            RegisterRequest registerRequest = new RegisterRequest(userid, userpassword, usertime, responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(registerRequest);
            Log.e("loginbtn click", "" + value);
            Logintask task = new Logintask();
            task.execute();
            Log.e("loginbtn click after", "" + value);

        } else if (v.getId() == R.id.textlogin) {

            TWBottomSheetDialog bottomSheetDialog = TWBottomSheetDialog.getInstance();
            bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");


        }
    }

    public void cheked() {

        if (booid == true && boopass == true) {

            loginbtn.setAlpha(1.0f);
            loginbtn.setClickable(true);
            loginbtn.setEnabled(true);

        } else {


            loginbtn.setAlpha(0.5f);
            loginbtn.setClickable(false);
            loginbtn.setEnabled(false);

        }

    }

    private String getTime() {        //서버로 전달할 접속 시간 체크
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    /*@Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }*/

    private class Logintask extends AsyncTask<Void, Void, Void> {


        CustomProgressDialog dialog = new CustomProgressDialog(LoginActivity.this);


        @Override
        protected void onPreExecute() {

            Log.e("Logintask onPreExecute", "" + value);
            dialog.show();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            if (value == 1) {


                Log.e("onPostExcute ", "" + value);
                dialog.dismiss();
                finish();
                Toast.makeText(getApplicationContext(), "로그인 성공!" + value, Toast.LENGTH_SHORT).show();
                Log.e("onPostExcute2 ", "" + value);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            } else if (value == 2) {


                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 일치하지 않습니다." + value, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else if (value == 3) {
                Toast.makeText(getApplicationContext(), "회원가입 성공 ! " + value, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }


        }


    }


}