package com.example.androidflux.stores;

import com.example.androidflux.actions.Action;
import com.example.androidflux.actions.MessageAction;
import com.example.androidflux.model.Message;
import com.squareup.otto.Subscribe;

public class MessageStore extends Store {
    private static MessageStore singleton;
    private Message mMessage = new Message();

    public MessageStore() {
        super();
    }

    public String getMessage() {
        return mMessage.getMessage();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case MessageAction.ACTION_NEW_MESSAGE:
                mMessage.setMessage((String) action.getData());
                break;
            default:
        }
        emitStoreChange();
    }

    @Override
    public  StoreChangeEvent changeEvent() {
        return new StoreChangeEvent();
    }

}
