package com.example.lg.twcircleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by charlie on 2017. 11. 22
 */

public class TWBottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener{

    public static TWBottomSheetDialog getInstance() { return new TWBottomSheetDialog(); }

    private LinearLayout msgLo;
    private LinearLayout emailLo;
    private LinearLayout cloudLo;
    private LinearLayout bluetoothLo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container,false);
        msgLo = (LinearLayout) view.findViewById(R.id.msgLo);
        emailLo = (LinearLayout) view.findViewById(R.id.emailLo);
        cloudLo = (LinearLayout) view.findViewById(R.id.cloudLo);
        bluetoothLo = (LinearLayout) view.findViewById(R.id.bluetoothLo);

        msgLo.setOnClickListener(this);
        emailLo.setOnClickListener(this);
        cloudLo.setOnClickListener(this);
        bluetoothLo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.msgLo:
                Toast.makeText(getContext(),"Message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.emailLo:
                Toast.makeText(getContext(),"Email", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cloudLo:
                Toast.makeText(getContext(),"Cloud", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bluetoothLo:
                Toast.makeText(getContext(),"Bluetooth", Toast.LENGTH_SHORT).show();
                break;
        }
        dismiss();
    }
}
