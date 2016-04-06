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
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

// In this case, the fragment displays simple text based on the page
public class Upload extends Fragment implements View.OnClickListener, MenuItem.OnMenuItemClickListener {
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
    ParseFile imageFile;
    ParseObject tipObject;
    Button upload;

    MenuItem submit;

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

        setHasOptionsMenu(true);

        content = (EditText) view.findViewById(R.id.content);
        company = (EditText) view.findViewById(R.id.company);
        tags = (EditText) view.findViewById(R.id.tags);
        //add image from gallery button
        imageToUpload = (ImageView) view.findViewById(R.id.imageToUpload);
        imageToUpload.setOnClickListener(this);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        submit = menu.findItem(R.id.submit);
        submit.setVisible(true);
        submit.setOnMenuItemClickListener(this);
        menu.findItem(R.id.action_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //submit tip
    @Override
    public boolean onMenuItemClick(MenuItem item) {
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
//          tipObject.put("Image", imageFile);
            tipObject.saveInBackground();

        } else {
            toast.show();
        }
        return false;
    }

    //Add a photo from gallery
    @Override
    public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
    }


    public void onActivityResult(Context main, int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
//            imageToUpload.setImageURI(selectedImage);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = main.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//
            // Convert it to byte
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // Compress image to lower quality scale 1 - 100
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] image = stream.toByteArray();

            // Create the ParseFile
            imageFile = new ParseFile("androidbegin.png", image);
            // Upload the image into Parse Cloud
            imageFile.saveInBackground();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            imgDecodableString = cursor.getString(columnIndex);
//            cursor.close();
//
//            // Set the Image in ImageView after decoding the String
//            imageToUpload.setImageBitmap(BitmapFactory
//                    .decodeFile(imgDecodableString));
        }
    }
}