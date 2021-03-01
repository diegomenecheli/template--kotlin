package com.templateapp.template

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.templateapp.template.view.ui.activities.LoginActivity

class App : Application(), LifecycleObserver {

    private var isAppInBackground = false

    override fun onCreate() {
        super.onCreate()

        val mainActivity: Intent
            mainActivity = Intent(this@App, LoginActivity::class.java)
            mainActivity.addFlags(FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or FLAG_ACTIVITY_NEW_TASK)
            mainActivity.action = Intent.ACTION_MAIN
            mainActivity.addCategory(Intent.CATEGORY_HOME)
            startActivity(mainActivity)

    }

    fun isAppInBackground(): Boolean {
        return isAppInBackground
    }

    private fun setAppInBackground(appInBackground: Boolean) {
        isAppInBackground = appInBackground
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        //App in background
        this.setAppInBackground(true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        this.setAppInBackground(false)
    }


}