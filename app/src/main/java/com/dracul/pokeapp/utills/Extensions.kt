package com.dracul.pokeapp.utills

import android.content.Context
import com.dracul.pokeapp.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun Context.getErrorMessage(throwable : Throwable):String{
    return when(throwable){
        is UnknownHostException ->this.getString(R.string.no_internet_connections)
        is SocketTimeoutException ->this.getString(R.string.bad_internet_connections)
        else -> {this.getString(R.string.unknown_error)}
    }
}