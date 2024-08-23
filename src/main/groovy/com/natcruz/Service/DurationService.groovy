package com.natcruz.Service

import jakarta.inject.Singleton

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Singleton
class DurationService {

    long getNumberOfDays(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.DAYS.between(start, end)
    }

    long getNumberOfWeeks(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.WEEKS.between(start, end)
    }

    long getNumberOfWeekdays(LocalDateTime start, LocalDateTime end) {
        DayOfWeek startDoW = start.getDayOfWeek()
        DayOfWeek endDoW = end.getDayOfWeek()

        def numberOfDays = getNumberOfDays(start, end)
        def numberOfWeeks = getNumberOfWeeks(start, end)
        // remove weekends
        def weekdays = numberOfDays-(2*numberOfWeeks)

        // adjust weekday count when not complete weeks
        if (numberOfDays % 7 !=0) {
            if(DayOfWeek.SUNDAY == startDoW){
                weekdays-=1
            }
            else if(DayOfWeek.SUNDAY == endDoW){
                weekdays-=1
            }
            else if(endDoW.value< startDoW.value) {
                weekdays-=2
            }
        }

        return weekdays
    }
}
