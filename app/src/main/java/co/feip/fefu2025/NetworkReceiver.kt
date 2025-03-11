package co.feip.fefu2025

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log


class NetworkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val connectivityManager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //connectivityManager.activeNetwork - возвращает объект Network, представляющий текущее активное сетевое подключение.
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            val connected = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
            Log.d("NetworkChangeReceiver", "Интернет-соединение ${if (connected) "доступно" else "недоступно"}")
        }
    }
}