package mx.edu.upqroo.kristenandroid.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build

import java.lang.ref.WeakReference

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import mx.edu.upqroo.kristenandroid.Application
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.ui.activities.MainActivity

/**
 * <h1>NotificationHelper</h1>
 * Class that handles the creation of notifications and the notifications channel for android
 * 8.0+.
 */
class NotificationHelper
/**
 * Private constructor, when called it generates a main notification channel.
 */
private constructor() {

    init {
        createNotificationChannel()
    }

    /**
     * Creates the notification channel, only for android 8.0+
     */
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_UNKNOWN)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_COMMUNICATION_INSTANT)
                    .build()

            val name = mContext!!.get()?.getString(R.string.channel_name)
            val description = mContext!!.get()?.getString(R.string.channel_description)
            val channel = NotificationChannel(CHANNEL_ID, name,
                    NotificationManager.IMPORTANCE_HIGH)

            channel.description = description
            channel.setSound(alarmSound, audioAttributes)
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = mContext!!.get()!!
                    .getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    /**
     * Create a notification
     * @param notificationId Notification id
     * @param title Title to display in the notification
     * @param content Content to display in the notification
     */
    fun createNotification(notificationId: Int, title: String, content: String) {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val vibrate = longArrayOf(0, 100, 200, 300)

        val intent = Intent(mContext!!.get(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(mContext!!.get(), 0, intent, 0)

        val mBuilder = NotificationCompat.Builder(mContext!!.get()!!, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(alarmSound)
                .setVibrate(vibrate)

        val notificationManager = NotificationManagerCompat.from(mContext!!.get()!!)
        notificationManager.notify(notificationId, mBuilder.build())
    }

    /**
     * Create a notification without id.
     * @param title Title to display in the notification
     * @param messageBody Message to display in the notification
     */
    fun createNotification(title: String, messageBody: String) {
        val intent = Intent(mContext!!.get(), MainActivity::class.java)
        val vibrate = longArrayOf(0, 100, 200, 300)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(mContext!!.get(), 0,
                intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(mContext!!.get()!!, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setVibrate(vibrate)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.drawable.ic_school_white_24dp)
        } else {
            notificationBuilder.setSmallIcon(R.drawable.launch_icon)
        }

        val notificationManager = mContext!!.get()!!
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())
    }

    /**
     * Create a notification whit a large text style.
     * @param notificationId Notification id
     * @param title Title to display
     * @param content Content to display
     * @param largeContent Large content to display
     */
    fun createNotification(notificationId: Int, title: String, content: String,
                           largeContent: String) {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val vibrate = longArrayOf(0, 100, 200, 300)

        val intent = Intent(mContext!!.get(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(mContext!!.get(), 0, intent, 0)

        val mBuilder = NotificationCompat.Builder(mContext!!.get()!!, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText(largeContent))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(alarmSound)
                .setVibrate(vibrate)

        val notificationManager = NotificationManagerCompat.from(mContext!!.get()!!)
        notificationManager.notify(notificationId, mBuilder.build())
    }

    companion object {
        private var mInstance: NotificationHelper? = null
        private const val CHANNEL_ID = "upqroo_notifications_channel"
        private var mContext: WeakReference<Context>? = null

        /**
         * Get the instance of this class, if it doesn't exists it creates it.
         * @return Returns this instance.
         */
        fun instance(): NotificationHelper {
            if (mInstance == null) {
                mContext = Application.appContext
                mInstance = NotificationHelper()
            }
            return mInstance as NotificationHelper
        }
    }
}
