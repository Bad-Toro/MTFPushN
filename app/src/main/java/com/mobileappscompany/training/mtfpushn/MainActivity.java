package com.mobileappscompany.training.mtfpushn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    IntentFilter mIF;
    BroadcastReceiver lbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("Trump");

        mIF = new IntentFilter("BadToro_My_Action");
        lbm = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s = intent.getStringExtra("K");
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ebHandler(MyEB_Event e) {
        String s = e.getEventMssg();
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent i = getIntent();
        if (i != null) {
            String s = i.getStringExtra("K");
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver(lbm,mIF);

        //EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver(lbm);
    }
}
