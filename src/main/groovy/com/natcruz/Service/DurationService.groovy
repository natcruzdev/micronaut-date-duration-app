package com.natcruz.Service

import jakarta.inject.Singleton
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Singleton
class DurationService {

    Integer getNumberOfDays(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.DAYS.between(start, end)
    }

    Integer getNumberOfWeeks(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.WEEKS.between(start, end)
    }
}
