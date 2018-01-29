package com.example.lg.twcircleapp;

import android.content.Context;
import android.view.View;
import android.widget.Button;

/**
 * Created by LG on 2018-01-29.
 */

public class Custombutton extends View {

    public Custombutton(Context context) {
        super(context);
    }

    @Override
    public void setEnabled(boolean enabled) {

        if (enabled == true){

            setBackgroundColor(R.color.colorAccent);
        }
    }
}
