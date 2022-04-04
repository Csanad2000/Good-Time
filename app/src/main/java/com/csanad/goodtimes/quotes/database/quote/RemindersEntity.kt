package com.csanad.goodtimes.quotes.database.quote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.csanad.goodtimes.Constants.REMINDERS_TABLE
import com.csanad.goodtimes.Reminder

@Entity(tableName = REMINDERS_TABLE)
class RemindersEntity(var reminder:Reminder) {
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}