package com.acadgildbatch5.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.acadgildbatch5.LOginage;
import com.acadgildbatch5.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Divyanshu on 05-02-2017.
 */

public class MyFirebaseMessingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessingService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG,"From: ="+remoteMessage.getFrom());
        Log.d(TAG,"Notification bay message: "+remoteMessage.getNotification().getBody());

        sendNotification(remoteMessage.getNotification().getBody());

    }


    private void sendNotification(String message ){
        Intent intent = new Intent(this, LOginage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultString = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder nBuilder= new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.greencheck)
                .setContentTitle("Arving Mediapp")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultString)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,nBuilder.build());
    }
}
