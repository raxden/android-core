/*
 * Copyright 2014 Ángel Gómez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.core.commons

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

object NetworkUtils {

    fun isNetworkAvailable(context: Context): Boolean = when (getConnectionType(context)) {
        1 -> true
        2 -> true
        else -> false
    }

    fun isWifiAvailable(context: Context): Boolean = when (getConnectionType(context)) {
        2 -> true
        else -> false
    }

    private fun getConnectionType(context: Context): Int {
        return getConnectionType(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }

    private fun getConnectionType(cm: ConnectivityManager): Int {
        return if (SDKUtils.hasMarshmallow()) {
            cm.getNetworkCapabilities(cm.activeNetwork).let {
                when {
                    it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> 2
                    it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> 1
                    else -> 0
                }
            }
        } else {
            cm.activeNetworkInfo.let {
                when {
                    it.type == ConnectivityManager.TYPE_WIFI -> 2
                    it.type == ConnectivityManager.TYPE_MOBILE -> 1
                    else -> 0
                }
            }
        }
    }
}
