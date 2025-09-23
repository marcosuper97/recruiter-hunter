package com.example.recruiterhunter.infrastructure.impl.network_checker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.recruiterhunter.domain.services.network_check.NetworkCheckService

class NetworkCheckServiceImpl(private val context: Context) : NetworkCheckService {
    private val connectivityService = Context.CONNECTIVITY_SERVICE
    private val connectivityManager = context.getSystemService(
        connectivityService
    ) as ConnectivityManager
    private val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    override suspend fun isInternetAvailable(): Boolean = capabilities?.run {
        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    } ?: false
}
