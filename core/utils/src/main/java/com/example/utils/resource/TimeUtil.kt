package com.example.utils.resource

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.FormatStyle
import java.util.Locale

object TimeUtil {
    fun currentTimeUtc(): Long = Instant.now().toEpochMilli()

    fun formatTimeWithDefaultPattern(utcTime: Long): String {
        val timeZone = ZoneId.systemDefault()
        val instant = Instant.ofEpochMilli(utcTime)
        val localDateTime = instant.atZone(timeZone)

        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withLocale(Locale.getDefault())
        return localDateTime.format(formatter)
    }

    fun parseTimeToUTCSystemDefaults(input: String): Long {
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withLocale(Locale.getDefault())
        val systemZone = ZoneId.systemDefault()
        val localDateTime = try { LocalDateTime.parse(input, formatter) } catch (e : DateTimeParseException) {LocalDateTime.now()}
        val zonedDateTime = localDateTime.atZone(systemZone)
        return zonedDateTime.toInstant().toEpochMilli()
    }
}