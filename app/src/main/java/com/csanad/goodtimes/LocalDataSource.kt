package com.csanad.goodtimes

import com.csanad.goodtimes.quotes.database.quote.QuotesDao
import com.csanad.goodtimes.quotes.database.quote.QuotesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(val dao:QuotesDao) {
    suspend fun insertQuotes(quotesEntity: QuotesEntity){
        dao.insertQuotes(quotesEntity)
    }

    suspend fun readQuote():Flow<List<QuotesEntity>>{
        return dao.readQuotes()
    }
}