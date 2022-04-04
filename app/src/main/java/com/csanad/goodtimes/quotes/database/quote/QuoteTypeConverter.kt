package com.csanad.goodtimes.quotes.database.quote

import androidx.room.TypeConverter
import com.csanad.goodtimes.Reminder
import com.csanad.goodtimes.quotes.api.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuoteTypeConverter {
    var gson=Gson()

    @TypeConverter
    fun quoteToString(quote: Quote):String{
        return gson.toJson(quote)
    }

    @TypeConverter
    fun stringToQuote(data:String):Quote{
        val listType=object: TypeToken<Quote>(){}.type
        return gson.fromJson(data,listType)
    }

    @TypeConverter
    fun reminderToString(reminder:Reminder):String{
        return gson.toJson(reminder)
    }

    @TypeConverter
    fun stringToReminder(data:String):Reminder{
        val listType=object: TypeToken<Reminder>(){}.type
        return gson.fromJson(data,listType)
    }
}