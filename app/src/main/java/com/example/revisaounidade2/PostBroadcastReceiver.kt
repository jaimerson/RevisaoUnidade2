package com.example.revisaounidade2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PostBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val FILTER_NAME = "somefilter"
        const val ACTION_NAME = "someaction"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context!!, "AAAAAA", Toast.LENGTH_SHORT).show()
        val postTitle = intent?.getStringExtra("postTitle")
        if (postTitle != null) {
            NotificationUtils.notificationSimple(context, "Post removed", postTitle)
        }
    }
}