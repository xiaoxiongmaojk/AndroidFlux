package com.example.androidflux.model;

public class Message{
    private String mText;
    public Message(){}

    public Message(String mText) {
        this.mText = mText;
    }

    public String getMessage() {
        return mText;
    }

    public void setMessage(String mText) {
        this.mText = mText;
    }
}