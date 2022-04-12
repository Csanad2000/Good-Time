package com.csanad.goodtimes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class StartupReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val startService=Intent(context,RemindersService::class.java)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            context.startForegroundService(startService)
        }else {
            context.startService(startService)
        }
        Log.i("GoodTimes","service started")
    }
}