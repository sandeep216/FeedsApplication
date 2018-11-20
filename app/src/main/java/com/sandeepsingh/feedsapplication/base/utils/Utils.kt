package com.sandeepsingh.feedsapplication.base.utils

import android.content.Context
import android.widget.Toast
import android.R.attr.x
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import com.google.gson.GsonBuilder
import com.sandeepsingh.feedsapplication.base.RetrofitServiceGenerator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.InetAddress
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Sandeep on 11/17/18.
 *
 * Utilities function.
 */
class Utils {

    companion object {

        /**
         * Used for showing toast for short duration
         * @param context : Context of activity which invoked this method.
         * @param message : Message to be shown in toast.
         */
        fun showShortToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        /**
         * Used for showing toast for longer duration
         * @param context : Context of activity which invoked this method.
         * @param message : Message to be shown in toast.
         */
        fun showLongToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        /**
         * Method to check internet connectivity
         * @param context : Context of activity which invoked this method.
         * @return Boolean value based on internet connectivity.
         */
        fun haveNetworkConnection(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
        }

        /**
         * This method provides retrofit instance.
         * @param url : Specify your base URL.
         * @return Retrofit instance with base url.
         */
        fun getRetrofitInstance(url: String): Retrofit {
            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            val _builder = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(RetrofitServiceGenerator.getGson()))

            val client = httpClient.build()
            return _builder.client(client).build()
        }
    }

}