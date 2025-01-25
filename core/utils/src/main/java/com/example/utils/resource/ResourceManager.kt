package com.example.utils.resource

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int, vararg args: Any?): String

}