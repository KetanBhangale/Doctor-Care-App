package com.ketub4.mvvmdemowithdi.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate

import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.ArrayList

class CalendarUtils {

    companion object{

        @RequiresApi(Build.VERSION_CODES.O)
        var selectedDate: LocalDate = LocalDate.now()

        @RequiresApi(Build.VERSION_CODES.O)
        fun formattedDate(date: LocalDate): String? {
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            return date.format(formatter)
        }
        @RequiresApi(Build.VERSION_CODES.O)
        fun formattedTime(time: LocalTime): String? {
            val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
            return time.format(formatter)
        }
        @RequiresApi(Build.VERSION_CODES.O)
        fun monthYearFromDate(date: LocalDate): String? {
            val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
            return date.format(formatter)
        }
        @RequiresApi(Build.VERSION_CODES.O)
        fun daysInMonthArray(date: LocalDate?): ArrayList<LocalDate?>? {
            val daysInMonthArray = ArrayList<LocalDate?>()
            val yearMonth = YearMonth.from(date)
            val daysInMonth = yearMonth.lengthOfMonth()
            val firstOfMonth: LocalDate = this.selectedDate!!.withDayOfMonth(1)
            val dayOfWeek = firstOfMonth.dayOfWeek.value
            for (i in 1..42) {
                if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) daysInMonthArray.add(null) else daysInMonthArray.add(
                    LocalDate.of(
                        this.selectedDate!!.year,
                        this.selectedDate!!.month,
                        i - dayOfWeek
                    )
                )
            }
            return daysInMonthArray
        }
        @RequiresApi(Build.VERSION_CODES.O)
        fun daysInWeekArray(selectedDate: LocalDate): ArrayList<LocalDate?> {
            val days = ArrayList<LocalDate?>()
            var current = sundayForDate(selectedDate)
            val endDate = current!!.plusWeeks(1)
            while (current!!.isBefore(endDate)) {
                days.add(current)
                current = current.plusDays(1)
            }
            return days
        }
        @RequiresApi(Build.VERSION_CODES.O)
        private fun sundayForDate(current: LocalDate): LocalDate? {
            var current = current
            val oneWeekAgo = current.minusWeeks(1)
            while (current.isAfter(oneWeekAgo)) {
                if (current.dayOfWeek == DayOfWeek.SUNDAY) return current
                current = current.minusDays(1)
            }
            return null
        }
    }
}