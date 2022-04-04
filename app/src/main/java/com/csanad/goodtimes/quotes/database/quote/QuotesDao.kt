package com.csanad.goodtimes.quotes.database.quote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.csanad.goodtimes.Constants.QUOTES_TABLE
import com.csanad.goodtimes.Constants.REMINDERS_TABLE
import com.csanad.goodtimes.Reminder
import com.csanad.goodtimes.quotes.api.Quote

@Dao
interface QuotesDao {
    @Insert
    suspend fun insertQuotes(quotesEntity: QuotesEntity)

    @Query("SELECT 1 FROM $QUOTES_TABLE")
    suspend fun readQuote():Quote

    @Insert
    suspend fun insertReminder(remindersEntity:RemindersEntity)

    @Query("SELECT 1 FROM $REMINDERS_TABLE")
    suspend fun readReminder():Reminder
}