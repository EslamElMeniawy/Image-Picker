package elmeniawy.eslam.imagepicker.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.CancellationException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

/**
 * NetworkUtils
 *
 * Created by Eslam El-Meniawy on 01-Feb-2020 at 11:43 AM.
 */

/**
 * Check if connected to Internet or not.
 *
 * @param context: Context instance to use in getting connectivity service.
 * @return A Boolean representing if connected or not or false if error.
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                )
    } else {
        @Suppress("DEPRECATION")
        val networkInfo = connectivityManager.activeNetworkInfo

        @Suppress("DEPRECATION")
        networkInfo != null && networkInfo.isConnected
    }
}

/**
 * Check if the provided throwable is caused by network issue.
 *
 * @param throwable: Throwable instance to check.
 * @return A Boolean representing if throwable caused by network issue.
 */
fun isNetworkThrowable(throwable: Throwable): Boolean = (throwable is ConnectException
        || throwable is SocketTimeoutException
        || throwable is UnknownHostException
        || throwable is TimeoutException
        || throwable is SSLHandshakeException)

/**
 * Check if the provided exception is caused by cancelling job.
 *
 * @param exception: Exception instance to check.
 * @return A Boolean representing if exception caused by cancelling job.
 */
fun isCancellationException(exception: Exception): Boolean = exception is CancellationException