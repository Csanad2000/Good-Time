package com.csanad.goodtimes

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class RemindersService : Service() {
    val receiver=ReminderReceiver()

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //receiver.setReminder(this,????)
        val activityIntent=Intent(baseContext,ReminderActivity::class.java)
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(activityIntent)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        //receiver.setReminder(this,????)
        val activityIntent=Intent(baseContext,ReminderActivity::class.java)
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(activityIntent)
        super.onStart(intent, startId)
    }

    override fun onDestroy() {
        Log.i("GoodTimes","service stopped")
        super.onDestroy()
    }
}