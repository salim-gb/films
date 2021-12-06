package com.example.films.services

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber


private const val CUSTOM_FIELD = "custom"
private const val PUSH_KEY_TITLE = "title"
private const val PUSH_KEY_MESSAGE = "message"
private const val CHANNEL_ID = "channel_id"
private const val NOTIFICATION_ID = "37"

class MessagingService : FirebaseMessagingService() {


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        FirebaseMessaging.getInstance().token.addOnSuccessListener {

        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val custom = message.data[CUSTOM_FIELD]
        Timber.i(custom ?: "no data!")

        if (message.data.isNotEmpty()) {
            handleDataMessage(message.data.toMap())
        }
    }

    private fun handleDataMessage(data: Map<String, String>) {
        val title = data[PUSH_KEY_TITLE]
        val message = data[PUSH_KEY_MESSAGE]

        if (!title.isNullOrBlank() && !message.isNullOrBlank()) {
            showNotification(title, message)
        }
    }

    private fun showNotification(title: String, message: String) {

    }
}