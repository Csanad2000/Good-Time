package com.csanad.goodtimes.quotes.database.quote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.csanad.goodtimes.Constants.QUOTES_TABLE
import com.csanad.goodtimes.quotes.api.Quote

@Entity(tableName=QUOTES_TABLE)
class QuotesEntity(var quote:Quote) {
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}