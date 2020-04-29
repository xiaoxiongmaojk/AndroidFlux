package com.example.androidflux;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidflux.actionCreator.MessageActionCreator;
import com.example.androidflux.dispatcher.Dispatcher;
import com.example.androidflux.stores.MessageStore;
import com.example.androidflux.stores.Store;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    private EditText vMessageEditor;
    private Button vMessageButton;
    private TextView vMessageView;

    private Dispatcher dispatcher;
    private MessageActionCreator messageActionCreator;
    private MessageStore messageStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDependencies();

        vMessageButton = findViewById(R.id.message_button);
        vMessageEditor = findViewById(R.id.message_editor);
        vMessageView = findViewById(R.id.message_view);

        vMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageActionCreator.SendMessage(vMessageEditor.getText().toString());
            }
        });
    }

    private void initDependencies() {
        dispatcher = Dispatcher.get();
        messageActionCreator = MessageActionCreator.getInstance(dispatcher);
        messageStore = new MessageStore();
        dispatcher.register(messageStore);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispatcher.unregister(messageStore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        messageStore.unregister(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        messageStore.register(this);
    }

    @Subscribe
    public void onStoreChange(Store.StoreChangeEvent event) {
        render(messageStore);
    }

    private void render(MessageStore store) {
        vMessageView.setText(store.getMessage());
    }
}
