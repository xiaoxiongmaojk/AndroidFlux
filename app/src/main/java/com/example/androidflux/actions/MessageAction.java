package com.example.androidflux.actions;

public class MessageAction extends Action<String> {
    public static final String ACTION_NEW_MESSAGE = "new_message";

    public MessageAction(String type, String data) {
        super(type, data);
    }
}
