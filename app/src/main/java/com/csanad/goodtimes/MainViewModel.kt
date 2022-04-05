package com.csanad.goodtimes

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.csanad.goodtimes.quotes.api.Result
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel @ViewModelInject constructor(private val repository: Repository,application: Application):AndroidViewModel(application) {
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