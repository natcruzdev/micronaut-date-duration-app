package com.natcruz.Controller

import com.natcruz.Service.DurationService
import io.micronaut.core.convert.format.Format
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

import java.time.LocalDateTime

@Controller("/duration")
class DurationController {

    @Inject
    DurationService durationService

    @Get("/days")
    @Produces(MediaType.TEXT_PLAIN)
    String getNumberOfDays(@Format("dd-MM-yyyy'T'HH:mm:ss") @QueryValue LocalDateTime start,
                           @Format("dd-MM-yyyy'T'HH:mm:ss") @QueryValue LocalDateTime end) {

        return durationService.getNumberOfDays(start, end) + " day(s)"
    }

    @Get("/weeks")
    @Produces(MediaType.TEXT_PLAIN)
    String getNumberOfWeeks(@Format("dd-MM-yyyy'T'HH:mm:ss") @QueryValue LocalDateTime start,
                            @Format("dd-MM-yyyy'T'HH:mm:ss") @QueryValue LocalDateTime end) {

        return durationService.getNumberOfWeeks(start, end) + " week(s)"
    }

    @Get("/weekdays")
    @Produces(MediaType.TEXT_PLAIN)
    String getNumberOfWeekdays(@Format("dd-MM-yyyy'T'HH:mm:ss") @QueryValue LocalDateTime start,
                            @Format("dd-MM-yyyy'T'HH:mm:ss") @QueryValue LocalDateTime end) {

        return durationService.getNumberOfWeekdays(start, end) + " week(s)"
    }
}
