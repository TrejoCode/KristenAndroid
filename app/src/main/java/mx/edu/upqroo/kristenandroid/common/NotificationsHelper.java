package mx.edu.upqroo.kristenandroid.common;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.activities.LoginActivity;

public class NotificationsHelper extends com.google.firebase.messaging.FirebaseMessagingService {

    public static String GENERAL_NOTIFICATION_KEY = "GEN";
    public static String CAREER_NOTIFICATION_KEY = "CAR";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            createNotification(title, message);
        }
    }

    @Override
    public void onDeletedMessages() { }

    private void createNotification(String title, String messageBody) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder
                = new NotificationCompat.Builder(this, "M_CH_ID")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(0 , notificationBuilder.build());
    }

    public static void SubscribeNotifications(String channel, String userChannel) {
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.subscribeToTopic(channel);
        firebaseMessaging.subscribeToTopic(userChannel);
        PreferencesManager.getInstance().saveNotificationsPreference(true, true);
    }

    public static void SubscribeNotifications(String channel) {
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.subscribeToTopic(channel);
    }

    public static void UnsuscribeNotifications(String channel) {
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.unsubscribeFromTopic(channel);
    }

    public static void UnsuscribeNotifications(String channel, String userChannel) {
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.unsubscribeFromTopic(channel);
        firebaseMessaging.unsubscribeFromTopic(userChannel);
        PreferencesManager.getInstance().saveNotificationsPreference(false, false);
    }
}
