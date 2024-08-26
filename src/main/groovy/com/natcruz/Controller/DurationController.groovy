package com.natcruz.Controller

import com.natcruz.Service.DurationService
import io.micronaut.core.convert.format.Format
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

import java.time.ZonedDateTime

@Controller("/duration")
class DurationController {

    @Inject
    DurationService durationService

    @Get("/days")
    @Produces(MediaType.TEXT_PLAIN)
    String getNumberOfDays(@Format("dd-MM-yyyy'T'HH:mm:ssZ") @QueryValue ZonedDateTime start,
                           @Format("dd-MM-yyyy'T'HH:mm:ssZ") @QueryValue ZonedDateTime end,
                           @QueryValue(defaultValue = "days") String convertTo) {

        return durationService.getNumberOfDays(start, end, convertTo.toUpperCase()) + " " +convertTo
    }

    @Get("/weeks")
    @Produces(MediaType.TEXT_PLAIN)
    String getNumberOfWeeks(@Format("dd-MM-yyyy'T'HH:mm:ssZ") @QueryValue ZonedDateTime start,
                            @Format("dd-MM-yyyy'T'HH:mm:ssZ") @QueryValue ZonedDateTime end,
                            @QueryValue(defaultValue = "weeks") String convertTo) {

        return durationService.getNumberOfWeeks(start, end, convertTo.toUpperCase()) + " " +convertTo
    }

    @Get("/weekdays")
    @Produces(MediaType.TEXT_PLAIN)
    String getNumberOfWeekdays(@Format("dd-MM-yyyy'T'HH:mm:ssZ") @QueryValue ZonedDateTime start,
                               @Format("dd-MM-yyyy'T'HH:mm:ssZ") @QueryValue ZonedDateTime end,
                               @QueryValue(defaultValue = "weekdays") String convertTo) {

        return durationService.getNumberOfWeekdays(start, end, convertTo.toUpperCase()) + " " +convertTo
    }
}
