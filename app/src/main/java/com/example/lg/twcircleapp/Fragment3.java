package com.example.lg.twcircleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment3 extends Fragment {

    public static Fragment3 newInstance() {
        Fragment3 fragment = new Fragment3();
        return fragment;
    }
        @Override
        public void onCreate (@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup
        container, @Nullable Bundle savedInstanceState){

            View view = inflater.inflate(R.layout.activity_fragment3, null);

            return view;


        }

}