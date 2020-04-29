package com.example.androidflux.dispatcher;

import com.example.androidflux.actions.Action;
import com.example.androidflux.stores.Store;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    private static Dispatcher instance;
    private final List<Store> stores = new ArrayList<>();

    public static Dispatcher get() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

    public Dispatcher() {}

    public void register(final Store store) {
        stores.add(store);
    }

    public void unregister(final Store store) {
        stores.remove(store);
    }

    public void dispatch(Action action) {
        post(action);
    }

    private void post(final Action action) {
        for (Store store : stores) {
            store.onAction(action);
        }
    }
}
