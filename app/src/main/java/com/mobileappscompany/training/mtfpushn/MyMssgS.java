package com.mobileappscompany.training.mtfpushn;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ferna on 6/1/2017.
 */

public class MyMssgS extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String  s = remoteMessage.getData().get("K");
//        Log.d("MAC_Tag", s);
//        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//        EventBus.getDefault().post(new MyEB_Event(s));

        Intent i = new Intent("BadToro_My_Action");
        i.putExtra("K", s);
        LocalBroadcastManager
                .getInstance(this)
                .sendBroadcast(i);

    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
