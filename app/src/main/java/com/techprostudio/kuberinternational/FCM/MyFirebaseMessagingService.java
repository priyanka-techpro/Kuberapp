package com.techprostudio.kuberinternational.FCM;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.SplashActivity;
import com.techprostudio.kuberinternational.Utils.AppPreference;


import androidx.core.app.NotificationCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
//    private RequestQueue queue;
//    URL url1;
//    Bitmap image;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
//        Log.d("msg", "onMessageReceived: " + remoteMessage.getData().get("message"));
//        Intent intent = new Intent(this, SplashActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//        String channelId = "Default";
//        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(remoteMessage.getNotification().getTitle())
//                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(pendingIntent);;
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
//            manager.createNotificationChannel(channel);
//        }
//        manager.notify(0, builder.build());


        try {
            //Displaying data in log
            //It is optional
            Log.e(TAG, "From: " + remoteMessage.getFrom());
            Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
            Log.e(TAG, "Notification Type: " + remoteMessage.getMessageType());
            Log.e(TAG, "Notification Data: " + remoteMessage.getData());

            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            Log.e("title",title);
            Log.e("message",message);


            // Check if message contains a data payload.
            if (remoteMessage.getData().size() > 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.getData());
              //  sendNotification(remoteMessage.getData());
                if (remoteMessage.getNotification()!=null) {
                    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                    scheduleJob();
                } else {
                    // Handle message within 10 seconds
                    handleNow();
                }

            }

            sendNotification(remoteMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        //Displaying token on logcat
        Log.e(TAG, "Refreshed token: " + token);

        String refreshedToken = token;

        if (refreshedToken != null) {
            new AppPreference(MyFirebaseMessagingService.this).saveDeviceToken(refreshedToken);
            // sendRegistrationToServer(refreshedToken);
        }
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token);
    }
    // [END on_new_token]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
    private void scheduleJob() {
        // [START dispatch_job]
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJob);
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }


    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(RemoteMessage data) {

        String title = "";
        String message = "";
        String type = "";
//        if (data != null && data.size() > 0) {
//          //  Log.e(TAG, "Notification Data payload size: " + data.size());
//            if (data.containsKey("title"))
//             //   Log.e(TAG, "Data title present " );
//                title = data.get("title");
//            //Log.e(TAG, "Data title value: "+title );
//            if (data.containsKey("body"))
//               // Log.e(TAG, "Data body present " );
//                message = data.get("body");
//          //  Log.e(TAG, "Data title value: "+message );
//            if (data.containsKey("type"))
//                // Log.e(TAG, "Data body present " );
//                type = data.get("type");
//        }
        if(data.getNotification() != null)
        {
            title = data.getNotification().getTitle();
            message = data.getNotification().getBody();
        }

        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("pushData", true);
//        intent.putExtra(IntentUtils.PUSH_TYPE, type);
//        intent.putExtra(IntentUtils.PUSH_TITLE, title);
//        intent.putExtra(IntentUtils.PUSH_MESSAGE, message);

        String defaultChannelId = getString(R.string.default_notification_channel_id);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.appicon);
//        Bitmap largeIcon = StringToBitMap(icon);


        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this,defaultChannelId)
                .setSmallIcon(R.drawable.appicon)
//                .setLargeIcon(largeIcon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MAX);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(defaultChannelId,
                    "Your Notifications",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());

//        notificationManager.notify(Utility.randInt(0, 800), notificationBuilder.build());
    }

//    private void sendRegistrationToServer(final String token) {
//        //You can implement this method to store the token on your server
//        //Not required for current project
//
//        queue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringPostRequest = new StringRequest(Request.Method.POST, ""/*Need to add url to send token to server*/, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e(TAG, "success response: " + response);
//                DeviceTokenModel deviceTokenModel = new DeviceTokenModel();
//
//                if (null == deviceTokenModel) {
//                    new AppPreference(MyFirebaseMessagingService.this).sendNotificationSubscription(false);
//                } else {
//                    new AppPreference(MyFirebaseMessagingService.this).sendNotificationSubscription(true);
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Error response: " + error);
////                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
////                params.put("X-API-KEY", "RANDOM@123");
//                return params;
//            }
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("device_token", token);
//                return params;
//            }
//        };
//        queue.add(stringPostRequest);
//
//    }

    /**
     * @param encodedString
     * @return bitmap (from given string)
     */
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

}
