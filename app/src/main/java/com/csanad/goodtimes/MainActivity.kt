package com.csanad.goodtimes

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.csanad.goodtimes.databinding.ActivityMainBinding
import com.csanad.goodtimes.quotes.database.quote.RemindersEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var navController:NavController
    lateinit var alarmManager:AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        alarmManager=getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()||super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

    fun setAlarm(remindersEntity: RemindersEntity){
        val intent=Intent(this,ReminderReceiver::class.java)
        val pendingIntent=PendingIntent.getBroadcast(this,remindersEntity.id,intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,remindersEntity.reminder.from+remindersEntity.reminder.time,pendingIntent)
    }
}