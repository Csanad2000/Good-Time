package com.csanad.goodtimes

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.csanad.goodtimes.quotes.database.quote.RemindersEntity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        TODO("Receiver.onReceive() is not implemented")
        /*
        when(intent.action){
            egyik -> {
                setReminder(context, intent.extras["reminder"])
            }
            másik -> {
                cancelReminder(context, intent.extras["reminder"])
            }
         }
         */
    }

    fun setReminder(context: Context,remindersEntity: RemindersEntity){
        val am=context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i =Intent(context, ReminderActivity::class.java)
        //i.putExtra("reminder",remindersEntity)
        val pi=PendingIntent.getBroadcast(context,remindersEntity.id,i,PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,remindersEntity.reminder.from+remindersEntity.reminder.time,pi)
    }

    fun cancelReminder(context: Context,remindersEntity: RemindersEntity){
        val am=context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val i =Intent(context, ReminderActivity::class.java)
        //i.putExtra("reminder",remindersEntity)
        val pi=PendingIntent.getBroadcast(context,remindersEntity.id,i,PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        am.cancel(pi)
    }
}