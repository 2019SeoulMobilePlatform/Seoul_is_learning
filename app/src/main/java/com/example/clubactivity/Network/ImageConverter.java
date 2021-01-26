package com.example.clubactivity.Network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.util.Log;

import com.example.clubactivity.AppManager;
import com.example.clubactivity.Club.ChatViewItem;
import com.example.clubactivity.Constants;

import java.io.ByteArrayOutputStream;

public class ImageConverter {

    public ImageConverter(){}

    public static String getImageToString(Bitmap userImage){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Bitmap dstBitmap = AppManager.getInstance().resize(userImage);

        dstBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] imageBytes = stream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return  encodedImage;
    }

    public static Bitmap getImageToBitmap(String encodedImage){

        byte[] decodedByte = Base64.decode(encodedImage, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static Bitmap getReplaceRegexToBitmap(String encodedImage){

        String replaceSlash = encodedImage.replaceAll("\\\\", "");
        String replaceImage = replaceSlash.replaceAll("\\\\n","\n");
        Log.e("replaceImage", replaceImage);

        byte[] decodedByte = Base64.decode(replaceImage, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
