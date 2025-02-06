package com.example.notes.resource

import android.content.Context

class AndroidResourceManager(private val context: Context) : ResourceManager {
    override fun getString(id: Int): String =
        context.resources.getString(id)

    override fun getString(id: Int, vararg args: Any?): String {
        return context.resources.getString(id, *args)
    }

}