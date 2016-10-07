package com.score.lambda.asyn;

import android.os.AsyncTask;
import android.util.Log;

import com.score.lambda.listener.ILambdaFetchListener;
import com.score.lambda.pojo.Lambda;
import com.score.lambda.util.LambdaParser;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Async task which handled fetching lambdas,
 *
 * @author erangaeb@gmail.com (eranga herath)
 */
public class LambdaFetcher extends AsyncTask<String, Void, String> {

    private static final String TAG = LambdaFetcher.class.getName();

    private ILambdaFetchListener listener;

    public LambdaFetcher(ILambdaFetchListener listener) {
        this.listener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String doInBackground(String... params) {
        return fetchLambdas(params[0]);
    }

    /**
     * HTTP get request to fetch lambdas
     *
     * @param uri uri
     * @return response
     */
    private String fetchLambdas(String uri) {
        Log.d(TAG, "Fetch lambdas with uri " + uri);

        HttpURLConnection connection = null;
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                // HTTP 200
                InputStream in = new BufferedInputStream(connection.getInputStream());
                return readStream(in);
            } else if (status == HttpURLConnection.HTTP_NOT_FOUND) {
                // HTTP 404
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }

        // means error
        return null;
    }

    /**
     * Convert input stream to string
     *
     * @param in input stream
     * @return string response
     * @throws IOException
     */
    private String readStream(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i = in.read();
        while (i != -1) {
            out.write(i);
            i = in.read();
        }

        return out.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        if (response == null) {
            listener.onFetchError();
        } else if (response.isEmpty()) {
            listener.onFetchEnd();
        } else {
            // have data
            Log.d(TAG, response);

            try {
                // parse and save lambdas
                ArrayList<Lambda> lambdaList = LambdaParser.getLambdaList(response);

                // TODO save lambdas from here

                listener.onFetchDone(lambdaList);

            } catch (JSONException e) {
                e.printStackTrace();
                listener.onFetchError();
            }
        }
    }
}
