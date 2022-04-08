package com.csanad.goodtimes

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat.getSystemService

import androidx.lifecycle.*
import com.csanad.goodtimes.quotes.api.Result
import com.csanad.goodtimes.quotes.database.quote.QuotesEntity
import com.csanad.goodtimes.quotes.database.quote.RemindersEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, application: Application):AndroidViewModel(application) {

    var readReminders: LiveData<List<RemindersEntity>> =repository.local.readReminder().asLiveData()
    var readQuotes:LiveData<List<QuotesEntity>> =repository.local.readQuote().asLiveData()

    fun insertReminders(remindersEntity: RemindersEntity)=viewModelScope.launch(Dispatchers.IO) {
        repository.local.insertReminders(remindersEntity)
        //AlarmManagerCompat.setExactAndAllowWhileIdle()
    }

    fun insertQuotes(quotesEntity: QuotesEntity)=viewModelScope.launch(Dispatchers.IO) {
        repository.local.insertQuotes(quotesEntity)
    }

    var quotesResponse:MutableLiveData<NetworkResult<Result>> = MutableLiveData()
    fun getQuotes()=viewModelScope.launch {
        getQuotesSafeCall()
    }

    private suspend fun getQuotesSafeCall() {
        quotesResponse.value=NetworkResult.Loading()
        if (hasInternetConnection()){
            try {
                val response=repository.remote.getMulti()
                quotesResponse.value=handleQuotesResponse(response)

                val result=quotesResponse.value!!.data
                if (result!=null){
                    offlineCacheQuotes(result)
                }
            }catch (e:Exception){
                quotesResponse.value=NetworkResult.Error("Quotes not found")
            }

        }else{
            quotesResponse.value=NetworkResult.Error("No internet connection")
        }
    }

    private fun offlineCacheQuotes(result: Result) {
        for (i in result.indices){
            val quotesEntity=QuotesEntity(result[i])
            insertQuotes(quotesEntity)
        }
    }

    suspend fun singleQuoteSafeCall(){
        quotesResponse.value=NetworkResult.Loading()
        if (hasInternetConnection()){
            try {
                val response=repository.remote.getQuote()
                quotesResponse.value=handleQuotesResponse(response)

                val result=quotesResponse.value!!.data
                if (result!=null){

                }
            }catch (e:Exception){
                quotesResponse.value=NetworkResult.Error("Quotes not found")
            }

        }else{
            quotesResponse.value=NetworkResult.Error("No internet connection")
        }
    }

    //TODO: Ez Api-tól függ
    private fun handleQuotesResponse(response: Response<Result>): NetworkResult<Result>? {
        when{
            response.message().toString().contains("timeout")->{
                return NetworkResult.Error("Timeout")
            }
            response.code()==402->{
                return NetworkResult.Error("API Key Limited")
            }
            response.body().isNullOrEmpty()->{
                return NetworkResult.Error("Quotes not found")
            }
            response.isSuccessful->{
                val result=response.body()
                return NetworkResult.Success(result!!)
            }
            else->{
                return NetworkResult.Error(response.message())
            }
        }
    }

    fun hasInternetConnection():Boolean{
        val connectivityManager=getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        )as ConnectivityManager

        val activeNetwork=connectivityManager.activeNetwork ?: return false
        val capabilities=connectivityManager.getNetworkCapabilities(activeNetwork) ?:return false
        return  when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
            else->false
        }
    }
}