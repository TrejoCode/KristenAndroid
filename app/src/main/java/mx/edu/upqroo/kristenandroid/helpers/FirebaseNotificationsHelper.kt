package mx.edu.upqroo.kristenandroid.helpers

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage

import mx.edu.upqroo.kristenandroid.managers.PreferencesManager

/**
 * <h1>FirebaseNotificationsHelper</h1>
 * This class receives the notifications send from Firebase and also has the methods to subscribe
 * and unsubscribe from the firebase notifications channels.
 */
class FirebaseNotificationsHelper : com.google.firebase.messaging.FirebaseMessagingService() {

    /**
     * Called when a new notification is send from firebase.
     * @param remoteMessage Content sent in the message
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val data = remoteMessage.data
        val title = data["title"]
        val body = data["body"]

        if (title != null && body != null) {
            NotificationHelper.instance().createNotification(title, body)
        }
    }

    override fun onDeletedMessages() {}

    companion object {
        const val GENERAL_NOTIFICATION_KEY = "GEN"
        const val CAREER_NOTIFICATION_KEY = "CAR"

        /**
         * Subscribe the application to a new channel from firebase.
         * @param channel Channel's name
         */
        fun subscribeNotifications(channel: String) {
            val firebaseMessaging = FirebaseMessaging.getInstance()
            firebaseMessaging.subscribeToTopic(channel)
        }

        /**
         * Unsubscribe the application from a firebase notification channel.
         * @param channel Channel's name
         */
        fun unsubscribeNotifications(channel: String) {
            val firebaseMessaging = FirebaseMessaging.getInstance()
            firebaseMessaging.unsubscribeFromTopic(channel)
        }

        /**
         * Unsubscribe from the general notifications channel and the user's channel.
         * @param channel General channel's name
         * @param userChannel User channel's name
         */
        fun unsubscribeNotifications(channel: String, userChannel: String) {
            val firebaseMessaging = FirebaseMessaging.getInstance()
            firebaseMessaging.unsubscribeFromTopic(channel)
            firebaseMessaging.unsubscribeFromTopic(userChannel)
            PreferencesManager.instance.saveNotificationsPreference(general = false, career = false)
        }
    }
}
