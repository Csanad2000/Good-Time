package com.csanad.goodtimes.reminders

import android.os.Parcelable
import android.text.Editable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reminder(
    @SerializedName("repetition")
    var repetition:Int,
    @SerializedName("days")
    var days: MutableList<Int>,
    @SerializedName("description")
    var description: Editable?,
    @SerializedName("from")
    var from: String,
    @SerializedName("time")
    var time: String
)