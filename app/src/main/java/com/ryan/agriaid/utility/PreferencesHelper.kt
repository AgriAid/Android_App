package com.ryan.agriaid.utility

import android.content.Context
import android.content.SharedPreferences


class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    fun setTermsAccepted(accepted: Boolean) {
        sharedPreferences.edit().putBoolean("terms_accepted", accepted).apply()
    }

    fun isTermsAccepted(): Boolean {
        return sharedPreferences.getBoolean("terms_accepted", false)
    }

    fun clearTermsAccepted() {
        sharedPreferences.edit().remove("terms_accepted").apply()
    }
}