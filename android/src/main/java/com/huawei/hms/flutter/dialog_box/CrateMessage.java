package com.huawei.hms.flutter.dialog_box;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.huawei.hms.flutter.dialog_box.R;

public class CrateMessage extends Activity {

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();

            String text = bundle.getString("key");

            // text will contain the string "your string message"
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                Message message = handler.obtainMessage();

                Bundle bundle = new Bundle();
                bundle.putString("key", "your string message");

                message.setData(bundle);

                handler.sendMessage(message);
            }
        };

        thread.start();
    }
}