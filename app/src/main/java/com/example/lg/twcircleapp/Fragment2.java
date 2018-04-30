package com.example.lg.twcircleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        return fragment;

    }

        @Override
        public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup
        container, @Nullable Bundle savedInstanceState){

            View view = inflater.inflate(R.layout.activity_fragment2, null);


            return view;


        }

}