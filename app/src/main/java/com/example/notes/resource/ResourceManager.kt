package com.example.notes.resource

import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int, vararg args: Any?): String

}