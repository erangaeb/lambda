package com.score.lambda.listener.listeners;

import com.score.lambda.pojo.pojo.Lambda;

import java.util.ArrayList;

public interface ILambdaFetchListener {
    void onFetchDone(ArrayList<Lambda> lambdaList);

    void onFetchError();

    void onFetchEnd();
}
