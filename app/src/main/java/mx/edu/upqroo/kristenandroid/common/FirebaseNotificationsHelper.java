package mx.edu.upqroo.kristenandroid.common;

import android.content.Context;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.lang.ref.WeakReference;

/**
 * <h1>FirebaseNotificationsHelper</h1>
 * This class receives the notifications send from Firebase and also has the methods to subscribe
 * and unsubscribe from the firebase notifications channels.
 */
public class FirebaseNotificationsHelper extends com.google.firebase.messaging.FirebaseMessagingService {
    public static String GENERAL_NOTIFICATION_KEY = "GEN";
    public static String CAREER_NOTIFICATION_KEY = "CAR";

    /**
     * Called when a new notification is send from firebase.
     * @param remoteMessage Content sent in the message
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            NotificationHelper.GetInstance(new WeakReference<Context>(this))
                    .createNotification(title, message);
        }
    }

    @Override
    public void onDeletedMessages() { }

    /**
     * Subscribe the application to a new channel from firebase.
     * @param channel Channel's name
     */
    public static void SubscribeNotifications(String channel) {
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.subscribeToTopic(channel);
    }

    /**
     * Unsubscribe the application from a firebase notification channel.
     * @param channel Channel's name
     */
    public static void UnsubscribeNotifications(String channel) {
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.unsubscribeFromTopic(channel);
    }

    /**
     * Unsubscribe from the general notifications channel and the user's channel.
     * @param channel General channel's name
     * @param userChannel User channel's name
     */
    public static void UnsubscribeNotifications(String channel, String userChannel) {
        FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
        firebaseMessaging.unsubscribeFromTopic(channel);
        firebaseMessaging.unsubscribeFromTopic(userChannel);
        PreferencesManager.getInstance().saveNotificationsPreference(false, false);
    }
}
