package com.csanad.goodtimes.quotes.database.quote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.csanad.goodtimes.Constants.QUOTES_TABLE
import com.csanad.goodtimes.quotes.api.Quote
import com.csanad.goodtimes.quotes.api.Result

@Entity(tableName=QUOTES_TABLE)
class QuotesEntity(var result:Result) {
    @PrimaryKey(autoGenerate = false)
    var id:Int=0
}