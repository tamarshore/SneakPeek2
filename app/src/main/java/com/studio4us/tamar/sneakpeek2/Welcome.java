package com.studio4us.tamar.sneakpeek2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;


/**
 * Created by Tamar on 4/6/16.
 */

public class Welcome  extends AppCompatActivity implements View.OnClickListener, MenuItem.OnMenuItemClickListener {
    EditText userName;
    String sUserName;
    MenuItem close;
    MenuItem submit;
    ParseObject userDetails;
    private ViewHolder holder;
    ImageView userImage;

    private UserDetails user = new UserDetails();
    private ParseFile imageFile;
    private static final int RESULT_LOAD_IMAGE = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        userName = (EditText) findViewById(R.id.user_name);

        saveUserName();

        userImage = (ImageView) findViewById(R.id.user_photo);
        ImageView uploadedImage = (ImageView) findViewById(R.id.uploadedImage);
        holder = new ViewHolder();
        holder.imageView = uploadedImage;
        userImage.setOnClickListener(this);
    }

    private void saveUserName() {
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sUserName = userName.getText().toString();
                if (!sUserName.matches("")) {
                    user.setUserName(sUserName);
                    submit.setVisible(true);
                    close.setVisible(false);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        close = menu.findItem(R.id.close);
        submit = menu.findItem(R.id.submit);
        close.setVisible(true);
        close.setOnMenuItemClickListener(this);
        submit.setOnMenuItemClickListener(this);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close:
                submit.setVisible(false);
                finish();
                break;
            case R.id.submit:
                userDetails = new ParseObject("UserDetails");
                userDetails.put("userName", sUserName);
                if(imageFile != null) {
                    userDetails.put("userImage", imageFile);
                }
                userDetails.saveInBackground();
                finish();
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
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
            user.setUserImage(bitmap);
//            holder.imageView.setImageBitmap(bitmap);
//
//            holder.imageView.setVisibility(View.VISIBLE);
//            userImage.setVisibility(View.INVISIBLE);
            bitmap.recycle();
            byte[] image = stream.toByteArray();


            // Create the ParseFile
            imageFile = new ParseFile("userImage.png", image);

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

    public class ViewHolder {
        ImageView imageView;
    }

}
