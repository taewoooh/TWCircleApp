package com.example.lg.twcircleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ItemFourFragment extends Fragment implements View.OnClickListener, View.OnKeyListener {


    CircleImageView imageView;
    ImageView proback;
    int check = 0;
    Toolbar toolbar;
    RelativeLayout relative;
    EditText username, content;
    String title;
    ImageView toolmenu, toolprofile, backkey;


    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;


    public static ItemFourFragment newInstance() {
        ItemFourFragment fragment = new ItemFourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getActivity(), "Four", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_item_four, container, false);


        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        content = (EditText) v.findViewById(R.id.content);
        username = (EditText) v.findViewById(R.id.username);
        username.setSelection(username.getText().length());
        imageView = (CircleImageView) v.findViewById(R.id.circle);
        proback = (ImageView) v.findViewById(R.id.proback);
        relative = (RelativeLayout) v.findViewById(R.id.relative);
        toolmenu = (ImageView) v.findViewById(R.id.toolmenu);
        backkey = (ImageView) v.findViewById(R.id.backkey);

        backkey.setOnClickListener(this);
        proback.setOnClickListener(this);
        toolmenu.setOnClickListener(this);
        imageView.setOnClickListener(this);


        username.setOnKeyListener(this);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.backkey:

                getActivity().finish();
                break;
            case R.id.proback:
                title = "배경화면";
                check = 1;
                showPictureDialog();

                break;
            case R.id.toolmenu:
                Toast.makeText(getActivity(), "테스트", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), OptionActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);

                break;
            case R.id.circle:

                title = "프로필";
                check = 2;
                showPictureDialog();


                break;

        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (v.getId()) {
            case R.id.username:
                if (keyCode == event.KEYCODE_ENTER) {
                    content.setFocusableInTouchMode(true);
                    content.setCursorVisible(true);
                    content.requestFocus();
                    return true;

                }

                break;


        }

        return false;
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle(title);
        String[] pictureDialogItems = {
                "사진촬영",
                "앨범에서 사진 가져오기",
                "기본 이미지"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhotoFromCamera();
                                break;
                            case 1:

                                choosePhotoFromGallary();
                                break;
                            case 2:

                                if (check == 1) {
                                    proback.setImageDrawable(null);

                                    Toast.makeText(getActivity(), "배경화면 변경했습니다." + check, Toast.LENGTH_SHORT).show();
                                } else if (check == 2) {
                                    //imageView.setImageResource(R.drawable.user);
                                    imageView.setImageDrawable(null);
                                    Toast.makeText(getActivity(), "프로필 사진 변경했습니다." + check, Toast.LENGTH_SHORT).show();

                                }


                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    if (check == 1) {
                        proback.setImageBitmap(bitmap);
                        Toast.makeText(getActivity(), "배경화면 변경했습니다.", Toast.LENGTH_SHORT).show();
                    } else if (check == 2) {
                        imageView.setImageBitmap(bitmap);
                        Toast.makeText(getActivity(), "프로필 사진 변경했습니다.", Toast.LENGTH_SHORT).show();

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

            saveImage(thumbnail);
            if (check == 1) {

                Toast.makeText(getActivity(), "배경화면 변경했습니다.", Toast.LENGTH_SHORT).show();
            } else if (check == 2) {
                imageView.setImageBitmap(thumbnail);
                Toast.makeText(getActivity(), "프로필 사진 변경했습니다.", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


}
