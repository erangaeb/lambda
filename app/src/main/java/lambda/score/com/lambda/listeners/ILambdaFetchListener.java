package lambda.score.com.lambda.listeners;

import java.util.ArrayList;

import lambda.score.com.lambda.pojo.Lambda;

public interface ILambdaFetchListener {
    public void onFetchDone(ArrayList<Lambda> lambdaList);

    public void onFetchError();

    public void onFetchEnd();
}
