package com.natcruz.Controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class DurationControllerTest extends Specification {

    @Inject
    @Client("/duration")
    HttpClient client

    def "GetNumberOfDays"() {
        given:
        UriBuilder uri = UriBuilder.of("/days")
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("convertTo", convertTo)

        when:
        HttpRequest<?> request = HttpRequest.GET(uri.toString()).accept(MediaType.TEXT_PLAIN)
        String body = client.toBlocking().retrieve(request)

        then:
        assert body == expected

        where:
        start                       | end                          |convertTo | expected
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "days"   | "365 days"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "seconds"| "31536000 seconds"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "minutes"| "525600 minutes"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "hours"  | "8760 hours"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "years"  | "1 years"

    }

    def "GetNumberOfWeeks"() {
        given:
        UriBuilder uri = UriBuilder.of("/weeks")
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("convertTo", convertTo)

        when:
        HttpRequest<?> request = HttpRequest.GET(uri.toString()).accept(MediaType.TEXT_PLAIN)
        String body = client.toBlocking().retrieve(request)

        then:
        assert body == expected

        where:
        start                       | end                          |convertTo | expected
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "weeks"  | "52 weeks"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "seconds"| "31449600 seconds"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "minutes"| "524160 minutes"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "hours"  | "8736 hours"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "years"  | "1 years"
    }

    def "GetNumberOfWeekdays"() {
        given:
        UriBuilder uri = UriBuilder.of("/weekdays")
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("convertTo", convertTo)

        when:
        HttpRequest<?> request = HttpRequest.GET(uri.toString()).accept(MediaType.TEXT_PLAIN)
        String body = client.toBlocking().retrieve(request)

        then:
        assert body == expected

        where:
        start                       | end                          |convertTo   | expected
        "23-08-2024T00:00:00+0000"  | "30-08-2024T00:00:00+0000"   | "weekdays" | "5 weekdays"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "weekdays" | "261 weekdays"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "seconds"  | "22550400 seconds"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "minutes"  | "375840 minutes"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "hours"    | "6264 hours"
        "23-08-2024T00:00:00+0000"  | "23-08-2025T00:00:00+0000"   | "years"    | "0 years"
    }
}
