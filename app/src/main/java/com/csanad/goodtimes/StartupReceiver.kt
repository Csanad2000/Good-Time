package com.csanad.goodtimes

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class StartupReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        TODO("StartupReceiver.onReceive() is not implemented")
        /*
        val startService=Intent(context,RemindersService::class.java)
        context.startService(startService)
        */
    }
}