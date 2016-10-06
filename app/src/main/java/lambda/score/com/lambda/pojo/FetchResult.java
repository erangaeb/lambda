package lambda.score.com.lambda.pojo;

/**
 * Created by eranga on 10/6/16.
 */

public class FetchResult {
    private int statusCode;
    private String response;

    public FetchResult(int statusCode, String response) {
        this.statusCode = statusCode;
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
