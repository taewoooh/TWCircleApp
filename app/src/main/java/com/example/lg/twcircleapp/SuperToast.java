package com.example.lg.twcircleapp;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

/**
 * Created by LG on 2018-01-29.
 */

public class SuperToast {

    public SuperToast(String message, String color, Context context) {

        SuperActivityToast.create(context, new Style(), Style.DURATION_SHORT)
                .setText(message)
                //.setDuration(Style.DURATION_SHORT)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setTextSize(5)
                .setTextColor(Color.parseColor("#ffffff"))
                .setGravity(Gravity.BOTTOM,0,0)
                .setColor(PaletteUtils.getSolidColor(color))
                .setAnimations(Style.FRAME_KITKAT).show();
    }
}
