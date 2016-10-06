package lambda.score.com.lambda.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import lambda.score.com.lambda.pojo.Lambda;

/**
 * Created by eranga on 10/6/16.
 */

public class LambdaPraser {
    public static ArrayList<Lambda> getLambdaList(String in) throws JSONException {
        ArrayList<Lambda> lambdaList = new ArrayList<Lambda>();

        JSONArray lambdas = new JSONArray(in);
        for (int i = 0; i < lambdas.length(); i++) {
            JSONObject lambda = lambdas.getJSONObject(i);
            lambdaList.add(new Lambda(lambda.getString("id"), lambda.getString("timestamp"), lambda.getString("text")));
        }

        return lambdaList;
    }
}
