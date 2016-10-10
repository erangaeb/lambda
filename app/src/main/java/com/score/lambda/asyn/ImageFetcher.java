package com.score.lambda.asyn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.score.lambda.pojo.Lambda;
import com.score.lambda.util.ImageUtils;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Fetch image asynchronously and set in the image view
 *
 * @author eranga herath(erangaeb@gmail.com)
 */
public class ImageFetcher extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = ImageFetcher.class.getName();

    private final WeakReference<RoundedImageView> imageViewWeakReference;
    private final Lambda lambda;

    public ImageFetcher(RoundedImageView imageViewWeakReference, Lambda lambda) {
        this.imageViewWeakReference = new WeakReference<>(imageViewWeakReference);
        this.lambda = lambda;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];

        return fetchImage(url);
    }

    /**
     * Download image and compress it
     *
     * @param url url
     * @return bitmap
     */
    private Bitmap fetchImage(String url) {
        Log.d(TAG, "Fetching image with url " + url);

        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Log.d(TAG, "Image downloaded");

                // resize bitmap first
                return ImageUtils.resizeBitmap(BitmapFactory.decodeStream(inputStream));
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        // set image
        ImageView imageView = imageViewWeakReference.get();
        if (imageView != null) {
            if (bitmap != null) {
                lambda.setImage(bitmap);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

}
