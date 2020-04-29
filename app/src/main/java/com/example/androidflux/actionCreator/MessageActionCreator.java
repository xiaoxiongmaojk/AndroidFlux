package com.example.androidflux.actionCreator;

import com.example.androidflux.actions.MessageAction;
import com.example.androidflux.dispatcher.Dispatcher;

public class MessageActionCreator {
    private static MessageActionCreator instance;
    final Dispatcher dispatcher;

    public MessageActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static MessageActionCreator getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new MessageActionCreator(dispatcher);
        }
        return instance;
    }

    public void SendMessage(String message) {
        dispatcher.dispatch(new MessageAction(MessageAction.ACTION_NEW_MESSAGE, message));
    }
}
