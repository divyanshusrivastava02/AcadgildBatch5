package com.acadgildbatch5.BroadcastReceiverSample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.acadgildbatch5.R;
import com.acadgildbatch5.animationExample.AnimationSample;
import com.acadgildbatch5.asyntaskExample.AsyncTaskSample;

/**
 * Created by Divyanshu on 08-01-2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
//Assign inbox style notification
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Inbox Notification");
        inboxStyle.addLine("Message 1.");
        inboxStyle.addLine("Message 2.");
        inboxStyle.addLine("Message 3.");
        inboxStyle.addLine("Message 4.");
        inboxStyle.addLine("Message 5.");
        inboxStyle.setSummaryText("+2 more");


        //Assign inbox style notification
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        bigText.setBigContentTitle("Big Text Notification");
        bigText.setSummaryText("By: Author of Lorem ipsum");


        Intent resultIntent = new Intent(context, AsyncTaskSample.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,0,resultIntent,0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context.getApplicationContext())
                .setSmallIcon(R.drawable.ic_menu_gallery)
                .setContentTitle("Hello Arvind")
                .setContentText("How are you brother!")
        .setStyle(bigText);
              //  .addAction(R.drawable.moon, "show activity", resultPendingIntent);

        mBuilder.setAutoCancel(true);



        mBuilder.setContentIntent(resultPendingIntent);



        //#########################################333
//        Intent resultIntent2 = new Intent(context, AnimationSample.class);
//       Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
//                R.drawable.moon);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.greencheck)
//                .setContentTitle("Big picture notification")
//                .setContentText("This is test of big picture notification.")
//                .setStyle(new Notification.BigPictureStyle().bigPicture(icon))
//                .addAction(R.drawable.ic_menu_camera, "show activity", resultPendingIntent)
//                .addAction(R.drawable.ic_menu_camera, "Share", PendingIntent.getActivity(context, 0, resultIntent2, 0, null));
//

        //############################################3

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(101,mBuilder.build());

    }
}
