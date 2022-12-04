package com.musinsa.jth.presentation.utils

import android.app.Activity
import android.content.Context
import android.net.*
import com.musinsa.jth.presentation.R
import com.musinsa.jth.presentation.extensions.showDlg

class NetworkUtil {
    private var currentContext : Context? =null

    fun setCurrentContext(context: Context?) {
        currentContext = context
    }

    private val networkCallBack = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {

        }

        override fun onLost(network: Network) {
            currentContext?.let {
                if(it is Activity) {
                    it.showDlg(it.getString(R.string.network_error))
                }
            }
        }
    }

    fun checkNetwork(): Boolean {
        val connectivityManager = currentContext?.getSystemService(ConnectivityManager::class.java)

        connectivityManager?.activeNetworkInfo?.let {
            return it.isConnectedOrConnecting
        } ?: return false
    }

    fun registerNetworkCallback() {
        val connectivityManager = currentContext?.getSystemService(ConnectivityManager::class.java)
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager?.registerNetworkCallback(networkRequest, networkCallBack)
    }

    fun terminateNetworkCallback(context : Context?) {
        val connectivityManager = context?.getSystemService(ConnectivityManager::class.java)
        connectivityManager?.unregisterNetworkCallback(networkCallBack)
    }

    fun networkNotConnect() {
        currentContext?.let {
            if(it is Activity) {
                it.showDlg(it.getString(R.string.network_error))
            }
        }
    }
}