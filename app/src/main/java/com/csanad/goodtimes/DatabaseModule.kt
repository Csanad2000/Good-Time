package com.csanad.goodtimes

import android.content.Context
import androidx.room.Room
import com.csanad.goodtimes.Constants.DATABASE_NAME
import com.csanad.goodtimes.quotes.database.quote.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context)=Room.databaseBuilder(context,QuoteDatabase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: QuoteDatabase)=database.quotesDao()
}