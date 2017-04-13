package com.stuhua.event;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.*;

public class MainActivity extends AppCompatActivity implements Observer {
    private TextView mTv;

    private static Handler sHandler = new Handler(Looper.getMainLooper()) {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = getViewByID(R.id.tv);
        EventBus.getDefault().register(this);
        Subject.getInstance().attach(this);
    }

    public <T extends View> T getViewByID(int id) {
        return (T) findViewById(id);
    }

    public void myEvent(View v) {
//        EventBus.getDefault().post(new MsgEvent("Hello EventBus!"));
        Subject.getInstance().post("liulihanhua....");
    }

    @Subscribe
    public void onMessage(MsgEvent event) {
        mTv.setText(event.getMsg().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Subject.getInstance().dettach(this);
    }

    @Override
    public void update(Object obj) {
        mTv.setText(obj.toString());
    }

    /**
     * Handler方法二：在线程中处理消息,使用handler.sendMessage 来发送消息
     */
    public void handlerMsg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                sHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };
                Looper.loop();
            }
        }).start();
    }

    /**
     * Handler方法一：使用post直接发送消息到主线程来更新UI， private static Handler sHandler = new Handler(Looper.getMainLooper()) {
     *};
     */
    public void sendMsg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 耗时操作
                 */
                sHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         更新UI
                         */
                        Toast.makeText(MainActivity.this, "更新UI", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
