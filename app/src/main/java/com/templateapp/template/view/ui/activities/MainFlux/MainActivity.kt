package com.templateapp.template.view.ui.activities.MainFlux

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.RemoteViews
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.template_android.R
import com.templateapp.template.services.actions.NotificationReceiver
import com.templateapp.template.services.actions.Utils
import com.templateapp.template.view.ui.activities.LoginActivity
import com.templateapp.template.view.ui.activities.Payments.CreditCardActivity
import com.templateapp.template.view.ui.activities.Payments.QRCodeActivity
import com.templateapp.template.view.ui.activities.SearchActivity
import com.templateapp.template.view.ui.activities.settings.SettingsInfosActivity
import com.templateapp.template.view.ui.activities.settings.SupportClientActivity
import kotlinx.android.synthetic.main.activity_main_menu_drawer.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelID = "com.templateapp.template.view.ui.activities.MainFlux"
    private val description = "Test"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val notificationReceiver = NotificationReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        this.registerReceiver(notificationReceiver, filter)


        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    val qrCodeActivity = Intent(applicationContext, QRCodeActivity::class.java)
                    startActivity(qrCodeActivity)
                }
                R.id.nav_search -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    val qrCodeActivity = Intent(applicationContext, SearchActivity::class.java)
                    startActivity(qrCodeActivity)
                }
                R.id.nav_gallery -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    val settingsAccount = Intent(applicationContext, SettingsInfosActivity::class.java)
                    startActivity(settingsAccount)
                }
                R.id.nav_slideshow -> {

                    drawer_layout.closeDrawer(GravityCompat.START)
                    val intent = Intent(this, LoginActivity::class.java)
                    val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

                    val contentView = RemoteViews(packageName, R.layout.notification_layout)
                    contentView.setTextViewText(R.id.title, "Template")
                    contentView.setTextViewText(R.id.notification_date, "Today")
                    contentView.setTextViewText(R.id.content, "An suas viderer pro. Vis cu magna altera, ex his vivendo atomorum.")
                    notificationChannel = NotificationChannel(channelID, description, NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(true)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(this, channelID)
                        .setContent(contentView)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setAutoCancel(true)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher_foreground))
                        .setContentIntent(pendingIntent)

                    notificationManager.notify(1234, builder.build())

                }
                R.id.nav_tools -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    val creditCardActivity = Intent(applicationContext, CreditCardActivity::class.java)
                    startActivity(creditCardActivity)
                }
                R.id.nav_share -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    val supportClient = Intent(applicationContext, SupportClientActivity::class.java)
                    supportClient.putExtra("menu", true)
                    startActivity(supportClient)
                }
                R.id.nav_send -> Utils.showToaster(
                    this,
                    resources.getString(R.string.toast_developing)
                )
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}

