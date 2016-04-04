package com.studio4us.tamar.sneakpeek2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;

// In this case, the fragment displays simple text based on the page
public class Upload extends Fragment  implements View.OnClickListener {
    //content of the tip
    EditText content;
    String tip;


    //company name
    EditText company;
    String name;

    //Tags
    EditText tags;
    String tagsString;

    //image to uploade
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageToUpload;
    String imgDecodableString;
    Button upload;





    public static Upload newInstance() {
        Bundle args = new Bundle();
        Upload upload = new Upload();
        upload.setArguments(args);
        return upload;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ulpoad_page, container, false);
        content = (EditText) view.findViewById(R.id.content);
        company = (EditText) view.findViewById(R.id.company);
        tags = (EditText) view.findViewById(R.id.tags);
        upload = (Button) view.findViewById(R.id.button);
        imageToUpload = (ImageView) view.findViewById(R.id.imageToUpload);

        imageToUpload.setOnClickListener(this);
        upload.setOnClickListener(this);

        return view;
    }



    @Override
    public void onClick(View v) {
        //Toast for empty tip
        CharSequence text = "Please insert content";
        CharSequence uploadingText = "Posting...";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), text, duration);
        Toast uploadingToast = Toast.makeText(getContext(), uploadingText, duration);

        tip = content.getText().toString();
        name = company.getText().toString();
        tagsString = tags.getText().toString();


        if (!tip.matches("")) {
//                uploadingToast.show();
            ParseFile imgFile = null;
            byte[] img_data = imageToUpload.toString().getBytes();
            ParseFile imageFile = new ParseFile("img_selected", img_data);
            imageFile.saveInBackground();

            ParseObject tipObject = new ParseObject("Tip");
            tipObject.put("TipContent", tip);
            tipObject.put("CompanyName", name);
            tipObject.put("Tags", tagsString);
            tipObject.put("Likes", 0);
            tipObject.put("Image", imageFile);
            tipObject.saveInBackground();
        }

        switch (v.getId()) {
            case R.id.imageToUpload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.button:

                break;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            ParseFile file = new ParseFile("Image", byteArray);
            file.saveInBackground();


//            ImageView imageView = (ImageView) getActivity().findViewById(R.id.imgView);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

//    private class UploadImage extends AsyncTask<Void, Void, Void>{
//
//        protected void doInBackground(Void...params){
//
//        }
//
//    }

}