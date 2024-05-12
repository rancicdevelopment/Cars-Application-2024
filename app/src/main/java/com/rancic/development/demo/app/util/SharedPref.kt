package com.rancic.development.demo.app.util

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SharedPref @Inject constructor(
    private val mPrefs: SharedPreferences,
    ) {
    companion object {
        const val PREF_KEY_CAR_LIST = "PREF_KEY_CAR_LIST"
    }


    fun writeToken(token: String?) { mPrefs.edit {putString(PREF_KEY_CAR_LIST, token) } }

    fun token() = mPrefs.getString(PREF_KEY_CAR_LIST, null)






}