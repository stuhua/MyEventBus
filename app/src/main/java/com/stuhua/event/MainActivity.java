package com.stuhua.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = getViewByID(R.id.tv);
        EventBus.getDefault().register(this);
    }

    public <T extends View> T getViewByID(int id) {
        return (T) findViewById(id);
    }

    public void myEvent(View v) {
        EventBus.getDefault().post(new MsgEvent("Hello EventBus!"));
    }

    @Subscribe
    public void onMessage(MsgEvent event) {
        mTv.setText(event.getMsg().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
