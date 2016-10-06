package com.score.lambda.asyn.asyn;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.score.lambda.listener.listeners.ILambdaFetchListener;
import com.score.lambda.util.util.LambdaPraser;

public class LambdaFetcher extends AsyncTask<String, Void, String> {

    private ILambdaFetchListener listener;

    public LambdaFetcher(ILambdaFetchListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        return doGet(params[0]);
    }

    private String doGet(String uri) {
        HttpURLConnection connection;
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                return readStream(in);
            } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // means error
        return null;
    }

    private String readStream(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i = in.read();
        while (i != -1) {
            out.write(i);
            i = in.read();
        }

        return out.toString();
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        if (response == null) {
            listener.onFetchError();
        } else if (response.isEmpty()) {
            listener.onFetchEnd();
        } else {
            // have data
            try {
                ArrayList lambdaList = LambdaPraser.getLambdaList(response);
                listener.onFetchDone(lambdaList);
            } catch (JSONException e) {
                e.printStackTrace();
                listener.onFetchError();
            }
        }
    }
}
