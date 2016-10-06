package lambda.score.com.lambda.asyn;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import lambda.score.com.lambda.listeners.ILambdaFetchListener;
import lambda.score.com.lambda.util.LambdaPraser;

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
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String out) {
        super.onPostExecute(out);

        if (out != null) {
            try {
                ArrayList lambdaList = LambdaPraser.getLambdaList(out);
                listener.onPostFetch(lambdaList);
            } catch (JSONException e) {
                e.printStackTrace();
                listener.onError();
            }
        } else {
            listener.onError();
        }
    }
}
