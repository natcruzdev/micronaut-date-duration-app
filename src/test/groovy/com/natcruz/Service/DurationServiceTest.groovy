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
        def result = durationService.getNumberOfDays(start, end)

        then:
        assert result == expected

        where:
        startText               | endText               | expected
        "23-08-2024T00:00:00"   | "23-08-2024T23:59:59" | 0
        "23-08-2024T01:00:00"   | "24-08-2024T00:00:00" | 0
        "23-08-2024T00:00:00"   | "24-08-2024T00:00:00" | 1
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | 30
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | 365
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | 365*4+1
    }

    def "GetNumberOfWeeks"() {
        def start = LocalDateTime.parse(startText, dtf)
        def end = LocalDateTime.parse(endText, dtf)

        when:
        def result = durationService.getNumberOfWeeks(start, end)

        then:
        assert result == expected

        where:
        startText               | endText               | expected
        "23-08-2024T00:00:00"   | "23-08-2024T23:59:59" | 0
        "23-08-2024T00:00:00"   | "24-08-2024T00:00:00" | 0
        "23-08-2024T00:00:00"   | "29-08-2024T23:59:59" | 0
        "23-08-2024T00:00:00"   | "30-08-2024T00:00:00" | 1
        "23-08-2024T00:00:00"   | "22-09-2024T00:00:00" | 4
        "23-08-2024T00:00:00"   | "23-08-2025T00:00:00" | 52
        "23-08-2024T00:00:00"   | "23-08-2028T00:00:00" | 52*4
    }
}
