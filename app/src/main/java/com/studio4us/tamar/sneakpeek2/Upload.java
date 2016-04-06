package com.studio4us.tamar.sneakpeek2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
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
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

// In this case, the fragment displays simple text based on the page
public class Upload extends Fragment implements View.OnClickListener {
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
    ParseFile imageFile;
    ParseObject tipObject;
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
        switch(v.getId()){
            //Image upload
            case R.id.imageToUpload:

//                Intent intent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, RESULT_LOAD_IMAGE);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), RESULT_LOAD_IMAGE );
                break;
            case R.id.button:
                //Toast for empty tip
                CharSequence text = "Please insert content";
                CharSequence uploadingText = "Posting...";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getContext(), text, duration);
                Toast uploadingToast = Toast.makeText(getContext(), uploadingText, duration);

                tip = content.getText().toString();
                name = company.getText().toString();
                tagsString = tags.getText().toString();

                //if the user inserted content
                if (!tip.matches("")) {
                    uploadingToast.show();

                    tipObject = new ParseObject("Tip");
                    tipObject.put("TipContent", tip);
                    tipObject.put("CompanyName", name);
                    tipObject.put("Tags", tagsString);
                    tipObject.put("Likes", 0);
                    tipObject.put("Image", imageFile);
                    tipObject.saveInBackground();


                } else {
                    toast.show();
                }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            boolean b = bitmap == null;
            //resizing the image
            int w = (int) (bitmap.getWidth() * 0.50);
            int h = (int) (bitmap.getHeight() * 0.50);
            bitmap = getResizedBitmap(bitmap, w , h);

            // Convert it to byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // Compress image to lower quality scale 1 - 100
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            bitmap.recycle();
            byte[] image = stream.toByteArray();


            // Create the ParseFile
            imageFile = new ParseFile("androidbegin.png", image);

            if (imageFile == null) {
                Log.d("gal-img", "is null");
            }
            // Upload the image into Parse Cloud
            imageFile.saveInBackground();
        }
    }

    //resizing the image to a selected 
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}