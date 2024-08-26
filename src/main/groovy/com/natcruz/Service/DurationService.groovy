package com.natcruz.Service

import jakarta.inject.Singleton

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import static java.time.temporal.ChronoUnit.*

@Singleton
class DurationService {

    long getNumberOfDays(LocalDateTime start, LocalDateTime end, String convertTo) {

        def numberOfDays = DAYS.between(start, end)
        return convertToUnit(numberOfDays, convertTo)
    }

    long getNumberOfWeeks(LocalDateTime start, LocalDateTime end, String convertTo) {
        def numberOfWeeks = WEEKS.between(start, end)
        if(WEEKS == ChronoUnit.valueOf(convertTo)){
            return numberOfWeeks
        }
        // Converting numberOfWeeks to days and then divide by number of days in a year would produce wrong answer.
        // e.g. 52 weeks x 7 days / 365 = 0 years
        // Better to divide numberOfWeeks by 52 complete weeks in a year when converting to years.
        else if(YEARS == ChronoUnit.valueOf(convertTo)) {
            return numberOfWeeks / 52
        }

        return convertToUnit(numberOfWeeks * WEEKS.getDuration().toDays(), convertTo)
    }

    long getNumberOfWeekdays(LocalDateTime start, LocalDateTime end, String convertTo) {
        DayOfWeek startDoW = start.getDayOfWeek()
        DayOfWeek endDoW = end.getDayOfWeek()

        def numberOfDays = getNumberOfDays(start, end, DAYS.name())
        def numberOfWeeks = getNumberOfWeeks(start, end, WEEKS.name())
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

        if("WEEKDAYS" == convertTo) {
            return weekdays
        }
        return convertToUnit(weekdays, convertTo)
    }

    private long convertToUnit(long numberOfDays, String convertTo) {
        def result = numberOfDays
        switch (ChronoUnit.valueOf(convertTo)) {
            case SECONDS:
                result = numberOfDays * DAYS.getDuration().toSeconds()
                break
            case MINUTES:
                result = numberOfDays * DAYS.getDuration().toMinutes()
                break
            case HOURS:
                result = numberOfDays * DAYS.getDuration().toHours()
                break
            case YEARS:
                result = numberOfDays / YEARS.getDuration().toDays()
                break
            default:
                break
        }
        return result
    }
}
