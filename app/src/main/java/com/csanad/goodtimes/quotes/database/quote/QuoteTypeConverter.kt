package com.csanad.goodtimes.quotes.database.quote

import android.text.Editable
import androidx.room.TypeConverter
import com.csanad.goodtimes.reminders.Reminder
import com.csanad.goodtimes.quotes.api.Quote
import com.csanad.goodtimes.quotes.api.Result
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
    fun resultToString(result:Result):String{
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data:String):Result{
        val listType=object: TypeToken<Result>(){}.type
        return gson.fromJson(data,listType)
    }

    @TypeConverter
    fun reminderToString(reminder: Reminder):String{
        return gson.toJson(reminder)
    }

    @TypeConverter
    fun stringToReminder(data:String): Reminder {
        val listType=object: TypeToken<Reminder>(){}.type
        return gson.fromJson(data,listType)
    }
}