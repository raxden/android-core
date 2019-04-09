package com.core.app.util

import android.content.Context
import android.os.Build
import com.raxdenstudios.preferences.AdvancedPreferences
import java.util.*
import android.annotation.TargetApi

class LocaleManager(
        private val mPreferences: AdvancedPreferences,
        private val mDefaultLocale: Locale,
        private val mAvailableLocaleList: Set<Locale>) {

    companion object {
        const val LOCALE_SELECTED = "locale_selected"
    }

    private var mLocale: Locale? = null

    fun updateBaseContextLocale(context: Context): Context {
        getSelected().also { locale ->
            Locale.setDefault(locale)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateResourcesLocale(context, locale)
            } else {
                updateResourcesLocaleLegacy(context, locale)
            }
        }
    }

    fun getAvailableLocaleList(): Set<Locale> = mAvailableLocaleList

    fun getDefault(): Locale = mDefaultLocale

    fun getDevice(): Locale = Locale.getDefault()

    fun getSelected(): Locale = mLocale ?: initLocale()

    fun setSelected(locale: Locale) {
        mPreferences.put(LOCALE_SELECTED, locale.toString()).commit()
        mLocale = locale
    }

    private fun initLocale(): Locale {
        val localeSelected = mPreferences.get(LOCALE_SELECTED, "")
        return if (localeSelected.isEmpty()) {
            var locale = getDefault()
            mAvailableLocaleList.forEach { availableLocale ->
                if (availableLocale.language == getDevice().language)
                    locale = getDevice()
            }
            setSelected(locale)
            locale
        } else {
            var locale = localeSelected.split("_".toRegex()).toTypedArray().let { Locale(it[0], it[1]) }
            mLocale = locale
            locale
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResourcesLocale(context: Context, locale: Locale): Context {
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    @Suppress("DEPRECATION")
    private fun updateResourcesLocaleLegacy(context: Context, locale: Locale): Context {
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}