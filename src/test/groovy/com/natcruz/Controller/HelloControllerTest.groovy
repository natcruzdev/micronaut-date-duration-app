package com.natcruz.Controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class HelloControllerTest extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    def "test hello world"() {
        when:
        HttpRequest<?> request = HttpRequest.GET("/hello").accept(MediaType.TEXT_PLAIN)
        String body = client.toBlocking().retrieve(request)

        then:
        assert body == "Hello World"

    }
}
