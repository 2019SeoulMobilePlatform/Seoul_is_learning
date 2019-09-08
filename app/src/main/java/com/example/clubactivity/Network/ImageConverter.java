package com.example.clubactivity.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class ImageConverter {

    public ImageConverter(){}

    public static String getImageToString(Bitmap userImage){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        userImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] imageBytes = stream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.e("EncodeToString", encodedImage);

        return  encodedImage;
    }

    public static Bitmap getImageToBitmap(String encodedImage){

        byte[] decodedByte = Base64.decode(encodedImage, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
