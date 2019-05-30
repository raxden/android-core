package com.core.app.util

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.preference.PreferenceManager
import timber.log.Timber
import java.util.*
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.core.commons.SDKUtils

object LocaleManager {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    private const val SELECTED_COUNTRY = "Locale.Helper.Selected.Country"

    fun attachBaseContext(context: Context): Context {
        val locale = getLocale(context)
        return updateResources(context, locale)
    }

    fun attachBaseContext(context: Context, locale: Locale): Context {
        persist(context, locale)
        return updateResources(context, locale)
    }

    fun onConfigurationChanged(context: Context): Context {
        val locale = getLocale(context)
        return updateResources(context, locale)
    }

    fun setLocale(context: Context, locale: Locale): Context {
        persist(context, locale)
        return updateResources(context, locale)
    }

    fun setLocale(context: Context, language: String, country: String): Context {
        val locale = persist(context, language, country)
        return updateResources(context, locale)
    }

    fun getLocale(context: Context): Locale = retrieve(context)

    private fun persist(context: Context, locale: Locale) {
        persist(context, locale.language, locale.country)
    }

    private fun persist(context: Context, language: String, country: String): Locale {
        PreferenceManager.getDefaultSharedPreferences(context).edit().run {
            putString(SELECTED_LANGUAGE, language)
            putString(SELECTED_COUNTRY, country)
            apply()
        }
        return Locale(language, country)
    }

    private fun retrieve(context: Context): Locale = PreferenceManager.getDefaultSharedPreferences(context).let {
        Locale(it.getString(SELECTED_LANGUAGE, Locale.getDefault().language), it.getString(SELECTED_COUNTRY, Locale.getDefault().country))
    }

    private fun updateResources(context: Context, locale: Locale): Context = when {
        SDKUtils.hasNougat() -> updateResourcesLocale(context, locale)
        else -> updateResourcesLocaleLegacy(context, locale)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResourcesLocale(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    @Suppress("DEPRECATION")
    private fun updateResourcesLocaleLegacy(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}