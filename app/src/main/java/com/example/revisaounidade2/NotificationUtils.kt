package com.example.revisaounidade2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

object NotificationUtils {
    val CHANNEL_ID = "default"

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context){
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelName = "Padrão"
        val channelDescription = "Canal padrão de notificações"
        val channel = NotificationChannel(
            CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = channelDescription
            enableLights(true)
            lightColor = Color.GREEN
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        }

        notificationManager.createNotificationChannel(channel)
    }

    fun notificationSimple(context: Context, title: String, content: String){
        notify(context, title, content)
    }

    fun notificationWithAction(context: Context){
        checkVersionAndCreateChannel(context)

        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.smiley_face)
            .setContentTitle("Minha notificação com AÇÃO")
            .setContentText("Texto da minha notificação com AÇÃO")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent))
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(getContentIntent(context))
            .setAutoCancel(true)
        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(2, notificationBuilder.build())
    }

    fun notificationBigText(context: Context){
        checkVersionAndCreateChannel(context)

        val bigTextStyle = NotificationCompat
            .BigTextStyle()
            .bigText("""
                Exemplo texto grande: Dolorem ipsum dolor sit amet consectetur exorcisamus te omni spiriti imundum omni satanica
                Exemplo texto grande: Dolorem ipsum dolor sit amet consectetur exorcisamus te omni spiriti imundum omni satanica
                Exemplo texto grande: Dolorem ipsum dolor sit amet consectetur exorcisamus te omni spiriti imundum omni satanica
                Exemplo texto grande: Dolorem ipsum dolor sit amet consectetur exorcisamus te omni spiriti imundum omni satanica
            """.trimIndent())

        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.smiley_face)
            .setContentTitle("Minha notificação - Big Text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent))
            .setDefaults(Notification.DEFAULT_ALL)
            .setStyle(bigTextStyle)
        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(3, notificationBuilder.build())
    }

    fun notificationActionWithButton(context: Context){
        checkVersionAndCreateChannel(context)

        val actionIntent = Intent(context, CreatePostActivity::class.java).apply {
            putExtra("something", "Ação da notificação")
        }

        val pendingIntent = PendingIntent.getBroadcast(context, 0, actionIntent, 0)

        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.smiley_face)
            .setContentTitle("Minha notificação com AÇÃO")
            .setContentText("Texto da minha notificação com AÇÃO")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent))
            .setDefaults(Notification.DEFAULT_ALL)
            .addAction(0, "Ação", pendingIntent)
            .setAutoCancel(true)
        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(4, notificationBuilder.build())
    }

    private fun notify(context: Context, title: String, text: String, callback: () -> Unit = {}){
        checkVersionAndCreateChannel(context)

        var notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.smiley_face)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(ActivityCompat.getColor(context, R.color.colorAccent))
            .setDefaults(Notification.DEFAULT_ALL)
            .apply {
                callback()
            }
        var notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(Random.nextInt(), notificationBuilder.build())
    }

    private fun getContentIntent(context: Context) : PendingIntent {
        val intent = Intent(context, CreatePostActivity::class.java).apply {
            putExtra("something", "batata")
        }

        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    private fun checkVersionAndCreateChannel(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }
    }
}
