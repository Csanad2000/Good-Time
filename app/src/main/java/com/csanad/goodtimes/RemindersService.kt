package com.csanad.goodtimes

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemindersService : Service() {
    val receiver=ReminderReceiver()

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //receiver.setReminder(this,????)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        //receiver.setReminder(this,????)
        super.onStart(intent, startId)
    }
}