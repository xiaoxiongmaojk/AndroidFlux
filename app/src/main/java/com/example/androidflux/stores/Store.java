package com.example.androidflux.stores;

import com.example.androidflux.actions.Action;
import com.squareup.otto.Bus;

public abstract class Store {
    private static final Bus bus = new Bus();

    protected Store() {
    }

    public void register(final Object view) {
        bus.register(view);
    }

    public void unregister(final Object view) {
        bus.unregister(view);
    }

    void emitStoreChange() {
        bus.post(changeEvent());
    }

    public abstract StoreChangeEvent changeEvent();

    public abstract void onAction(Action action);

    public class StoreChangeEvent {
    }
}
