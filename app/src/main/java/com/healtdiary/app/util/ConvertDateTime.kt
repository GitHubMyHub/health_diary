package com.healtdiary.app.util

import java.util.*

class ConvertDateTime {

    fun getCalendar(): String {
        val cldr: Calendar = Calendar.getInstance()

        val day = cldr.get(Calendar.DAY_OF_MONTH)
        val month = cldr.get(Calendar.MONTH) + 1
        val year = cldr.get(Calendar.YEAR)

        val hours = cldr.get(Calendar.HOUR_OF_DAY) + 2
        val minutes = cldr.get(Calendar.MINUTE)

        return day.toString() + "." + month.toString() + "." + year.toString() + " - " + hours + ":" + minutes
    }
}