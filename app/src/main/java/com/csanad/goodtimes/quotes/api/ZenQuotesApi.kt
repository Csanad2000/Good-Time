package com.csanad.goodtimes.quotes.api

import com.csanad.goodtimes.Constants.MULTI_QUOTE
import com.csanad.goodtimes.Constants.RANDOM_QUOTE
import retrofit2.Response
import retrofit2.http.GET

interface ZenQuotesApi {
    @GET(RANDOM_QUOTE)
    suspend fun getQuote(): Response<Result>

    @GET(MULTI_QUOTE)
    suspend fun getMulti(): Response<Result>
}