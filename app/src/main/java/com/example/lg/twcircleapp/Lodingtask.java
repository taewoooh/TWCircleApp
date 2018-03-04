package com.example.lg.twcircleapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by LG on 2018-02-21.
 */

public class Lodingtask extends AsyncTask<Void, Void, Void> {

    Context context;
    static int value = 0;
    CustomProgressDialog dialog;

    public Lodingtask(Context con) {
        this.context = con;
        dialog = new CustomProgressDialog(context);
    }

    public Lodingtask(Context con, int value) {
        this.context = con;
        this.value = value;
        dialog = new CustomProgressDialog(context);
    }


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
            //finish();
            Toast.makeText(context, "로그인 성공!" + value, Toast.LENGTH_SHORT).show();
            Log.e("onPostExcute2 ", "" + value);

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        } else if (value == 2) {
            Intent intent = new Intent(context, DialogActivity.class);
            context.startActivity(intent);
            dialog.dismiss();
        } else if (value == 3) {
            Toast.makeText(context, "회원가입 성공 ! " + value, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            dialog.dismiss();
        }


    }


}