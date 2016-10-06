package com.score.lambda.pojo.pojo;

/**
 * Created by eranga on 10/6/16.
 */

public class Lambda {
    private String id;
    private String timestamp;
    private String text;

    public Lambda(String id, String timestamp, String text) {
        this.id = id;
        this.timestamp = timestamp;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
