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
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by Sandeep on 11/17/18.
 */
class Utils {

    companion object {
        fun showShortToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun showLongToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        fun haveNetworkConnection(context: Context): Boolean {
            var haveConnectedWifi = false
            var haveConnectedMobile = false

            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val networks = connectivityManager.allNetworks
                var networkInfo: NetworkInfo
                for (mNetwork in networks) {
                    networkInfo = connectivityManager.getNetworkInfo(mNetwork)
                    if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                        if (networkInfo.typeName.equals("WIFI", ignoreCase = true)) {
                            haveConnectedWifi = true
                            if (haveConnectedMobile)
                                break
                        } else if (networkInfo.typeName.equals("MOBILE", ignoreCase = true)) {
                            haveConnectedMobile = true
                            if (haveConnectedWifi)
                                break
                        }
                    }
                }
            } else {
                if (connectivityManager != null) {

                    val info = connectivityManager.allNetworkInfo
                    if (info != null) {
                        for (networkInfo in info) {
                            if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                                if (networkInfo.typeName.equals("WIFI", ignoreCase = true)) {
                                    haveConnectedWifi = true
                                    if (haveConnectedMobile)
                                        break
                                } else if (networkInfo.typeName.equals("MOBILE", ignoreCase = true)) {
                                    haveConnectedMobile = true
                                    if (haveConnectedWifi)
                                        break
                                }
                            }
                        }
                    }
                }
            }
            return haveConnectedWifi || haveConnectedMobile
        }

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