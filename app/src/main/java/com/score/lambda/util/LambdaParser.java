package com.score.lambda.util;

import com.score.lambda.pojo.Lambda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LambdaParser {
    public static ArrayList<Lambda> getLambdaList(String in) throws JSONException {
        ArrayList<Lambda> lambdaList = new ArrayList<>();

        JSONArray lambdas = new JSONArray(in);
        for (int i = 0; i < lambdas.length(); i++) {
            JSONObject lambda = lambdas.getJSONObject(i);
            lambdaList.add(new Lambda(lambda.getString("id"), lambda.getLong("time"), lambda.getString("text")));
        }

        return lambdaList;
    }
}
