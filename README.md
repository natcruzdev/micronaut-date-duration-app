## Micronaut Date Duration App
This is a simple Web API service for getting the number of days/weekdays or complete weeks between two DateTime 
parameters using [Groovy](https://groovy-lang.org/documentation.html) on [Micronaut Framework](https://micronaut.io/).

## Local Dev Setup
Before getting started on the app, you need to:

1. Install [git](https://git-scm.com/).
2. Install an IDE such as  [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/)
3. Install [SDKMAN](https://sdkman.io/)

## Project Setup
### Install JDK 17
```shell
$ sdk install java 17.0.12-amzn
```
Check if installed properly:
```shell
$ java -version
openjdk version "17.0.12" 2024-07-16 LTS
OpenJDK Runtime Environment Corretto-17.0.12.7.1 (build 17.0.12+7-LTS)
OpenJDK 64-Bit Server VM Corretto-17.0.12.7.1 (build 17.0.12+7-LTS, mixed mode, sharing)
```

### Clone project
```shell
$ git clone https://github.com/natcruzdev/micronaut-date-duration-app.git
```

Navigate to the project
```shell
$ cd micronaut-date-duration-app
```

## Running
### Run the app
Start the application (while using Java 17)
```shell
$ sdk use java 17.0.12-amzn
$ chmod +x gradlew
$ ./gradlew run
```
Access http://localhost:8080/hello to check if the app is running.

To run the tests
```shell
$ ./gradlew test
```

#### Accessing the APIs
http://localhost:8080/duration

There are three accessible endpoints defined in `DurationController.groovy`
1. `/days` - to get the number of days between two DateTime parameters.
2. `/weekdays` - to get the number of weekdays between two DateTime parameters.
3. `/weeks` - to get the number of complete weeks between two DateTime parameters.

The APIs accept the DateTime query parameters with keys -
`start` and `end`. It also accepts third parameter with key `convertTo` to convert
the result of (1,2, or 3) into one of seconds, minutes, hours, years.

Accepted date time format is `dd-MM-yyyy'T'HH:mm:ssZ`

#### Example
##### 1. Get number of days between
`start` = 23-08-2024T00:00:00+0000 and `end` = 23-08-2025T00:00:00+0000 .
- Note that `+` sign for the timezone offset input is URL encoded to `%2B`.

http://localhost:8080/duration/days/?start=23-08-2024T00:00:00%2B0000&end=23-08-2025T00:00:00%2B0000

Returns
`365 days`

- Convert to seconds.

Just add `convertTo=seconds` in the query parameter

http://localhost:8080/duration/days/?start=23-08-2024T00:00:00%2B0000&end=23-08-2025T00:00:00%2B0000&convertTo=seconds

Returns`31536000 seconds`

- Convert to minutes

http://localhost:8080/duration/days/?start=23-08-2024T00:00:00%2B0000&end=23-08-2025T00:00:00%2B0000&convertTo=minutes

Returns `525600 minutes`

- Convert to hours

http://localhost:8080/duration/days/?start=23-08-2024T00:00:00%2B0000&end=23-08-2025T00:00:00%2B0000&convertTo=hours

Returns `8760 hours`

- Convert to years

http://localhost:8080/duration/days/?start=23-08-2024T00:00:00%2B0000&end=23-08-2025T00:00:00%2B0000&convertTo=years

Returns `1 years`

##### 2. Get number of weekdays between
`start` = 23-08-2024T00:00:00+0000 and `end` = 30-08-2024T00:00:00+0000 .
- Note that `+` sign for the timezone offset input is URL encoded to `%2B`.

http://localhost:8080/duration/weekdays/?start=23-08-2024T00:00:00%2B0000&end=30-08-2024T00:00:00%2B0000

Returns `5 weekdays`

##### 3. Get number of weeks between
`start` = 23-08-2024T00:00:00+0000 and `end` = 30-08-2024T00:00:00+0000 .
- Note that `+` sign for the timezone offset input is URL encoded to `%2B`.

http://localhost:8080/duration/weeks/?start=23-08-2024T00:00:00%2B0000&end=30-08-2024T00:00:00%2B0000

Returns `1 weeks`