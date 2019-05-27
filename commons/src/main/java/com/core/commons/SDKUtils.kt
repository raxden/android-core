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

import android.os.Build

object SDKUtils {

    val isEmulator: Boolean
        get() = (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || "google_sdk" == Build.PRODUCT)

    /**
     * Checks if the device has Lolllipop or higher version.
     * @return `true` if device is a tablet.
     */
    fun hasLollipop(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

    /**
     * Checks if the device has Marshmallow or higher version.
     * @return `true` if device is a tablet.
     */
    fun hasMarshmallow(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    /**
     * Checks if the device has Marshmallow or higher version.
     * @return `true` if device is a tablet.
     */
    fun hasNougat(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
}
