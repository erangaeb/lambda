package com.score.lambda.listener;

import com.score.lambda.pojo.Lambda;

import java.util.ArrayList;

/**
 * Listen for lambda fetch events
 *
 * @author erangaeb@gmail.com (eranga herath)
 */
public interface ILambdaFetchListener {
    void onFetchDone(ArrayList<Lambda> lambdaList);

    void onFetchError();

    void onFetchEnd();
}
