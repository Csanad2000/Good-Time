package com.csanad.goodtimes

import android.content.Context
import androidx.room.Room
import com.csanad.goodtimes.Constants.QUOTE_DATABASE_NAME
import com.csanad.goodtimes.quotes.database.quote.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context)=Room.databaseBuilder(context,QuoteDatabase::class.java,QUOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: QuoteDatabase)=database.quotesDao()
}