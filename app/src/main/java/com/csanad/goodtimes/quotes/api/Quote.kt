package com.csanad.goodtimes.quotes.api


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("q")
    val quote: String,
    @SerializedName("a")
    val author: String,
    @SerializedName("c")
    val count: String
)