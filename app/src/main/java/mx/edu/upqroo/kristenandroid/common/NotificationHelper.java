package mx.edu.upqroo.kristenandroid.common;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import java.lang.ref.WeakReference;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.activities.LoginActivity;
import mx.edu.upqroo.kristenandroid.activities.MainActivity;

/**
 * <h1>NotificationHelper</h1>
 * Class that handles the creation of notifications and the notifications channel for android
 * 8.0+.
 */
public class NotificationHelper {
    private static NotificationHelper mInstance;
    private static String CHANNEL_ID = "UPQROO_NOTIFICATIONS_CHANNEL";
    private static WeakReference<Context> mContext;

    /**
     * Private constructor, when called it generates a main notification channel.
     */
    private NotificationHelper() {
        createNotificationChannel();
    }

    /**
     * Get the instance of this class, if it doesn't exists it creates it.
     * @param context context needed when creating notifications.
     * @return Returns this instance.
     */
    static NotificationHelper GetInstance(WeakReference<Context> context) {
        mContext = context;
        if (mInstance == null) {
            mInstance = new NotificationHelper();
        }
        return mInstance;
    }

    /**
     * Creates the notification channel, only for android 8.0+
     */
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();

            CharSequence name = mContext.get().getString(R.string.channel_name);
            String description = mContext.get().getString(R.string.channel_description);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name,
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription(description);
            channel.setSound(alarmSound, audioAttributes);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = mContext.get()
                    .getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    /**
     * Create a notification
     * @param notificationId Notification id
     * @param title Title to display in the notification
     * @param content Content to display in the notification
     */
    public void createNotification(int notificationId, String title, String content) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] vibrate = { 0, 100, 200, 300 };

        Intent intent = new Intent(mContext.get(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent
                = PendingIntent.getActivity(mContext.get(), 0, intent, 0);

        NotificationCompat.Builder mBuilder
                = new NotificationCompat.Builder(mContext.get(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(alarmSound)
                .setVibrate(vibrate);

        NotificationManagerCompat notificationManager
                = NotificationManagerCompat.from(mContext.get());
        notificationManager.notify(notificationId, mBuilder.build());
    }

    /**
     * Create a notification without id.
     * @param title Title to display in the notification
     * @param messageBody Message to display in the notification
     */
    void createNotification(String title, String messageBody) {
        Intent intent = new Intent(mContext.get(), LoginActivity.class);
        long[] vibrate = { 0, 100, 200, 300 };
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext.get(), 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder
                = new NotificationCompat.Builder(mContext.get(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setVibrate(vibrate);

        NotificationManager notificationManager =
                (NotificationManager) mContext.get().getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(0 , notificationBuilder.build());
    }

    /**
     * Create a notification whit a large text style.
     * @param notificationId Notification id
     * @param title Title to display
     * @param content Content to display
     * @param largeContent Large content to display
     */
    public void createNotification(int notificationId, String title, String content,
                                   String largeContent) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] vibrate = { 0, 100, 200, 300 };

        Intent intent = new Intent(mContext.get(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent
                = PendingIntent.getActivity(mContext.get(), 0, intent, 0);

        NotificationCompat.Builder mBuilder
                = new NotificationCompat.Builder(mContext.get(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(largeContent))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(alarmSound)
                .setVibrate(vibrate);

        NotificationManagerCompat notificationManager
                = NotificationManagerCompat.from(mContext.get());
        notificationManager.notify(notificationId, mBuilder.build());
    }
}
