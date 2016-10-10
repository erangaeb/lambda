package com.score.lambda.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Image utilities
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class ImageUtils {
    /**
     * Resize bitmap into thumbnail size
     *
     * @param bitmap bitmap
     * @return resized bitmap
     */
    public static Bitmap resizeBitmap(Bitmap bitmap) {
        final int THUMBNAIL_SIZE = 64;

        bitmap = Bitmap.createScaledBitmap(bitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        return BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);
    }
}
