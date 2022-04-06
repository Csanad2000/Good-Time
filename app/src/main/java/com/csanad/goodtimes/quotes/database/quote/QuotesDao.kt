package com.csanad.goodtimes.quotes.database.quote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csanad.goodtimes.Constants.QUOTES_TABLE
import com.csanad.goodtimes.Constants.REMINDERS_TABLE
import com.csanad.goodtimes.reminders.Reminder
import com.csanad.goodtimes.quotes.api.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(quotesEntity: QuotesEntity)

    @Query("SELECT * FROM $QUOTES_TABLE")
    fun readQuotes(): Flow<List<QuotesEntity>>

    @Insert
    suspend fun insertReminder(remindersEntity:RemindersEntity)

    @Query("SELECT * FROM $REMINDERS_TABLE ORDER BY id ASC")
    fun readReminder(): Flow<List<RemindersEntity>>
}