package com.score.lambda.listeners;

import com.score.lambda.pojo.Lambda;

import java.util.ArrayList;

public interface ILambdaFetchListener {
    void onFetchDone(ArrayList<Lambda> lambdaList);

    void onFetchError();

    void onFetchEnd();
}
