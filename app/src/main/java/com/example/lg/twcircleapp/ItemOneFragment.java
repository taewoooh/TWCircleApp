package com.example.lg.twcircleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ItemOneFragment extends Fragment {
    public static ItemOneFragment newInstance() {
        ItemOneFragment fragment = new ItemOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getActivity(),"One", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_one, container, false);
    }
}