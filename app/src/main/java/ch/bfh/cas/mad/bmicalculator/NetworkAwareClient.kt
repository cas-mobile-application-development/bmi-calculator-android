package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.io.IOException

class NetworkAwareClient(context: Context) {
    enum class NetworkType {
        WIFI, ETHERNET, CELLULAR, OTHER
    }

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    suspend fun <T> executeWhenOnline(block: suspend () -> T): T {
        if (!isOnline()) {
            throw IOException("No network available")
        }

        return block()
    }

    suspend fun <T> executeWhenWifi(block: suspend () -> T): T {
        val networkType = getNetworkType()
        return when (networkType) {
            NetworkType.WIFI, NetworkType.ETHERNET -> block()
            else -> throw IOException("No WIFI or Ethernet available")
        }
    }

    private fun isOnline(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun getNetworkType(): NetworkType? {
        val activeNetwork = connectivityManager.activeNetwork ?: return null
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return null
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NetworkType.ETHERNET
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkType.WIFI
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkType.CELLULAR
            else -> NetworkType.OTHER
        }
    }
}