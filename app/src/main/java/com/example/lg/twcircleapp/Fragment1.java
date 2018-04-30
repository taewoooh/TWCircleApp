package com.example.lg.twcircleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment {

    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }



        @Nullable
        @Override
        public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup
        container, @Nullable Bundle savedInstanceState){

            View view = inflater.inflate(R.layout.activity_fragment1, null);


            return view;
        }

}