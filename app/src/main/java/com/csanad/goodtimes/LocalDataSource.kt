package com.csanad.goodtimes

import com.csanad.goodtimes.quotes.database.quote.QuotesDao
import com.csanad.goodtimes.quotes.database.quote.QuotesEntity
import com.csanad.goodtimes.quotes.database.quote.RemindersEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(val dao:QuotesDao) {
    suspend fun insertQuotes(quotesEntity: QuotesEntity){
        dao.insertQuotes(quotesEntity)
    }

    fun readQuote():Flow<List<QuotesEntity>>{
        return dao.readQuotes()
    }

    suspend fun insertReminders(remindersEntity: RemindersEntity){
        dao.insertReminder(remindersEntity)
    }

    fun readReminder(): Flow<List<RemindersEntity>> {
        return dao.readReminder()
    }
}