package com.example.budalajedna.nfctryout.datahandling;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class PictureTransform {

    public String getBitmapString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    public Bitmap decodeBitmapString(String string){
        InputStream stream = new ByteArrayInputStream(Base64.decode(string.getBytes(),Base64.DEFAULT));
        return BitmapFactory.decodeStream(stream);
    }
}
