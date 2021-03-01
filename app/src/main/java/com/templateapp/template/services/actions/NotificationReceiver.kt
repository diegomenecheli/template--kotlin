package com.templateapp.template.services.actions

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.template_android.R


class NotificationReceiver : BroadcastReceiver() {

    private var context: Context? = null
    private lateinit var channel: NotificationChannel
    private var isChannelCreated = false
    private val EVENT_CHANNEL_ID = "EVENT_CHANNEL_ID"


    override fun onReceive(context: Context?, intent: Intent?) {


        this.context = context
        val action = intent?.action
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        var networkType = ""

        capabilities ?: return

        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                networkType = "Wifi"
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                networkType = "Cellular"
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                networkType = "Ethernet"
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                networkType = "Bluetooth"
            }
        }

        when (action) {
            Intent.ACTION_POWER_CONNECTED -> {
                notifyUser()
            }
            Intent.ACTION_POWER_DISCONNECTED -> {

            }
        }
    }

    private fun notifyUser() {

        if (!isChannelCreated) {
            createChannel()
        }
        val bm = getCircleBitmap(
            BitmapFactory.decodeResource(
                context!!.resources,
                R.mipmap.ic_launcher_foreground
            )
        )

        val mBuilder = NotificationCompat.Builder(context!!, EVENT_CHANNEL_ID)
            .setLargeIcon(bm)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Watch your Plants!")
            .setContentText("Your Redbud is blooming!")
        val notification = mBuilder.build()
        val notificationManagerCompat = NotificationManagerCompat.from(context!!)
        notificationManagerCompat.notify(1, notification)

    }


    private fun createChannel() {
        channel = NotificationChannel(
            EVENT_CHANNEL_ID,
            "Plant Events",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "A channel for plant events"
        channel.lightColor = Color.GREEN
        val notificationManager = getSystemService(context!!, NotificationManager::class.java)
        notificationManager!!.createNotificationChannel(channel)
        isChannelCreated = true
    }

    fun getCircleBitmap(bitmap: Bitmap): Bitmap? {
        val output: Bitmap
        val srcRect: Rect
        val dstRect: Rect
        val r: Float
        val width = bitmap.width
        val height = bitmap.height
        if (width > height) {
            output = Bitmap.createBitmap(height, height, Bitmap.Config.ARGB_8888)
            val left = (width - height) / 2
            val right = left + height
            srcRect = Rect(left, 0, right, height)
            dstRect = Rect(0, 0, height, height)
            r = height / 2.toFloat()
        } else {
            output = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888)
            val top = (height - width) / 2
            val bottom = top + width
            srcRect = Rect(0, top, width, bottom)
            dstRect = Rect(0, 0, width, width)
            r = width / 2.toFloat()
        }
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        paint.setAntiAlias(true)
        canvas.drawARGB(0, 0, 0, 0)
        paint.setColor(color)
        canvas.drawCircle(r, r, r, paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint)
        bitmap.recycle()
        return output
    }
}