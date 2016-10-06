package com.score.lambda.pojo;

/**
 * Created by eranga on 10/6/16.
 */

public class Lambda {
    private String id;
    private long timestamp;
    private String text;
    private boolean isSelected;

    public Lambda(String id, long timestamp, String text, boolean isSelected) {
        this.id = id;
        this.timestamp = timestamp;
        this.text = text;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
