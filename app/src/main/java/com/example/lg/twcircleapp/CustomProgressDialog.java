package com.example.lg.twcircleapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

/**
 * Created by LG on 2018-01-31.
 */

public class CustomProgressDialog extends Dialog {

    public CustomProgressDialog(Context context) {
        super(context);
        setContentView(R.layout.custom_dialog); // 다이얼로그 xml
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //배경 투명 처리



    }
}
