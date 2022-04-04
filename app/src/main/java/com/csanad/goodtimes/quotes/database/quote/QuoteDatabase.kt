package com.csanad.goodtimes.quotes.database.quote

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [QuotesEntity::class,RemindersEntity::class], version = 1,exportSchema = false)
@TypeConverters(QuoteTypeConverter::class)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun quotesDao(): QuotesDao
}