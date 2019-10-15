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
package com.core.app.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.core.app.extension.closeSilently
import com.core.app.extension.readContent
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.io.ObjectInputStream
import java.util.*

object AssetsUtils {

    fun getDrawable(context: Context, name: String): Drawable? {
        var drawable: Drawable? = null
        var inputStream: InputStream? = null
        try {
            inputStream = context.assets.open(name)
            drawable = Drawable.createFromStream(inputStream, name)
        } catch (e: IOException) {
            Timber.e(e)
        } finally {
            inputStream?.closeSilently()
        }
        return drawable
    }

    fun getString(context: Context, name: String): String? {
        var value: String? = null
        var inputStream: InputStream? = null
        try {
            inputStream = context.assets.open(name)
            value = inputStream.readContent()
        } catch (e: IOException) {
            Timber.e(e)
        } finally {
            inputStream?.closeSilently()
        }
        return value
    }

    fun getProperties(context: Context, name: String): Properties? {
        var properties: Properties? = null
        var inputStream: InputStream? = null
        try {
            inputStream = context.assets.open(name)
            properties = Properties()
            properties.load(inputStream)
        } catch (e: IOException) {
            Timber.e(e)
        } finally {
            inputStream?.closeSilently()
        }
        return properties
    }

    fun <T> getData(context: Context, name: String): T? {
        var data: T? = null
        var inputStream: InputStream? = null
        var ois: ObjectInputStream? = null
        try {
            inputStream = context.assets.open(name)
            ois = ObjectInputStream(inputStream)
            try {
                data = ois.readObject() as T
            } catch (e: ClassNotFoundException) {
                Timber.e(e)
            }
        } catch (e: IOException) {
            Timber.e(e)
        } finally {
            ois?.closeSilently()
            inputStream?.closeSilently()
        }
        return data
    }

}
