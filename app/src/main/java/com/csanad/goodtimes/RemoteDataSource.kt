package com.csanad.goodtimes

import com.csanad.goodtimes.quotes.api.Result
import com.csanad.goodtimes.quotes.api.ZenQuotesApi
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val api: ZenQuotesApi) {
    suspend fun getMulti(): Response<Result> {
        return api.getMulti()
    }

    suspend fun getQuote():Response<Result>{
        return api.getQuote()
    }
}