package com.natcruz.Service

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@MicronautTest
class DurationServiceTest extends Specification {

    @Inject
    DurationService durationService

    DateTimeFormatter dtf

    def setup() {
        dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss")
    }

    def "GetNumberOfDays"() {
        given:
        def start = LocalDateTime.parse(startText, dtf)
        def end = LocalDateTime.parse(endText, dtf)

        when:
        def result = durationService.getNumberOfDays(start, end, convertTo)

        then:
        assert result == expected

        where:
        startText               | endText               | convertTo | expected
        "23-08-2024T00:00:00"   | "23-08-2024T23:59:59" | "DAYS"    | 0
        "23-08-2024T01:00:00"   | "24-08-2024T00:00:00" | "DAYS"    | 0
        "23-08-2024T00:00:00"   | "24-08-2024T00:00:00" | "DAYS"    | 1
        "23-08-2024T00:00:00"   | "24-08-2024T00:00:00" | "HOURS"   | 24
        "23-08-2024T00:00:00"   | "24-08-2024T00:00:00" | "MINUTES" | 24*60
        "23-08-2024T00:00:00"   | "24-08-2024T00:00:00" | "SECONDS" | 24*60*60
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "DAYS"    | 7
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "HOURS"   | 7*24
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "MINUTES" | 7*24*60
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "SECONDS" | 7*24*60*60
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "DAYS"    | 30
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "HOURS"   | 30*24
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "MINUTES" | 30*24*60
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "SECONDS" | 30*24*60*60
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "DAYS"    | 365
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "HOURS"   | 365*24
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "MINUTES" | 365*24*60
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "SECONDS" | 365*24*60*60
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "YEARS"   | 1
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | "DAYS"    | 365*4+1
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | "YEARS"   | 4
    }

    def "GetNumberOfWeeks"() {
        def start = LocalDateTime.parse(startText, dtf)
        def end = LocalDateTime.parse(endText, dtf)

        when:
        def result = durationService.getNumberOfWeeks(start, end, convertTo)

        then:
        assert result == expected

        where:
        startText               | endText               | convertTo | expected
        "23-08-2024T00:00:00"   | "23-08-2024T23:59:59" | "WEEKS"   | 0
        "23-08-2024T00:00:00"   | "24-08-2024T00:00:00" | "WEEKS"   | 0
        "23-08-2024T00:00:00"   | "29-08-2024T23:59:59" | "WEEKS"   | 0
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "WEEKS"   | 1
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "SECONDS" | 1*7*24*60*60
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "MINUTES" | 1*7*24*60
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "HOURS"   | 1*7*24
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "YEARS"   | 0
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "WEEKS"   | 4
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "SECONDS" | 4*7*24*60*60
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "MINUTES" | 4*7*24*60
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "HOURS"   | 4*7*24
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | "YEARS"   | 0
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "WEEKS"   | 52
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "SECONDS" | 52*7*24*60*60
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "MINUTES" | 52*7*24*60
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "HOURS"   | 52*7*24
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | "YEARS"   | 1
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | "WEEKS"   | 52*4
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | "SECONDS" | 52*4*7*24*60*60
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | "MINUTES" | 52*4*7*24*60
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | "HOURS"   | 52*4*7*24
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | "YEARS"   | 4
    }

    def "GetNumberOfWeekdays"() {
        def start = LocalDateTime.parse(startText, dtf)
        def end = LocalDateTime.parse(endText, dtf)

        when:
        def result = durationService.getNumberOfWeekdays(start, end, convertTo)

        then:
        assert result == expected

        where:
        startText               | endText               | convertTo  | expected
        "23-08-2024T00:00:00"   | "23-08-2024T23:59:59" | "WEEKDAYS" | 0
        "23-08-2024T00:00:00"   | "28-08-2024T00:00:00" | "WEEKDAYS" | 3
        "23-08-2024T00:00:00"   | "28-08-2024T00:00:00" | "SECONDS"  | 3*24*60*60
        "23-08-2024T00:00:00"   | "28-08-2024T00:00:00" | "MINUTES"  | 3*24*60
        "23-08-2024T00:00:00"   | "28-08-2024T00:00:00" | "HOURS"    | 3*24
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "WEEKDAYS" | 5
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "SECONDS"  | 5*24*60*60
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "MINUTES"  | 5*24*60
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "HOURS"    | 5*24
        "23-08-2024T00:00:00"   | "31-08-2024T00:00:00" | "WEEKDAYS" | 6
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | "WEEKDAYS" | 5
        "23-08-2024T00:00:00"   | "04-09-2024T00:00:00" | "WEEKDAYS" | 8
        //start falls on Sunday
        "18-08-2024T00:00:00"   | "04-09-2024T00:00:00" | "WEEKDAYS" | 12
        "18-08-2024T00:00:00"   | "04-09-2024T00:00:00" | "SECONDS"  | 12*24*60*60
        "18-08-2024T00:00:00"   | "04-09-2024T00:00:00" | "MINUTES"  | 12*24*60
        "18-08-2024T00:00:00"   | "04-09-2024T00:00:00" | "HOURS"    | 12*24
        //end falls on Sunday
        "23-08-2024T00:00:00"   | "01-09-2024T00:00:00" | "WEEKDAYS" | 6
        "23-08-2024T00:00:00"   | "01-09-2024T00:00:00" | "SECONDS"  | 6*24*60*60
        "23-08-2024T00:00:00"   | "01-09-2024T00:00:00" | "MINUTES"  | 6*24*60
        "23-08-2024T00:00:00"   | "01-09-2024T00:00:00" | "HOURS"    | 6*24
        // 365 weekdays
        "23-08-2024T00:00:00"   | "16-01-2026T00:00:00" | "WEEKDAYS" | 365
        "23-08-2024T00:00:00"   | "16-01-2026T00:00:00" | "YEARS"    | 1
    }
}
