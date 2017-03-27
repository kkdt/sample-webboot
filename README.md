# sample-webboot

This sample includes a very basic Swing application defined by a Spring XML context file. Within that context, is a shared model `CounterModel` that just increment a counter and publishes it to the Swing GUI. This application is bootstrapped within a Spring Boot `ApplicationContext` and the same model is used in an exposed RESTful endpoint (GET method).

The Swing application is a `JFrame` and the RESTful endpoint is at `http://localhost:8080/counter`. Navigating a browser to the URL (and refreshing) will get the current counter value that is also displayed in the Swing UI.

This is a prototype for integrating a Spring-based Swing application and a Spring Boot web application while exploring a proof-of-concept for migrating the thick-client into a web-based application. With the assumption that the legacy Swing application is Spring-heavy, the overall approach for migration into a thin-client is combine both Swing and Spring Boot `ApplicationContext`s into a single context sharing appropriate components/beans (data providers, models, etc) that can be utilized with the web components (i.e. controllers, views, endpoints, etc), and iteratively deprecate legacy Swing views.

## Quick Start

1. Build: `gradle cleanAll assemble`
2. Run the program: `java -jar build/libs/sample-webboot.jar`
3. You should see a `JFrame` popup that displays an incrementing counter model
4. Navigate to `http://localhost:8080/counter` to get the counter model value
5. Logs are stored in `logs/webboot.log` wherever you invoked the jar

For convenience, the runnable jar is also checked into the `run` directory. Double-click it or run it via Java from command line using the snippet above.

## Software Stack

1. Java 1.8.0_112
2. Spring Boot 1.3.3.RELEASE (with Jetty)
3. Log4j
4. Gradle 3.3 (with spring-boot plugin 1.1.8.RELEASE)

## Version 0.1 

Goals/questions explored

1. Spring Boot API for bootstrapping and custom application initialization
2. Can a thick client Swing application coexist with Spring Boot web application container?
3. Can both Swing and Web application context share beans?

## Version 0.2

Goals/questions explored

1. Integrate Spring Security such that Swing authenticates and any access to web resources prior to logging into the Swing client is immediately returned as an UNAUTHORIZED
2. Explore Spring Boot opinionated Spring Security hookups

## Notes

Both the Swing and web applications are sharing the same `CounterModel` bean.

The Swing part is just a standard application context XML file `swingContext.xml` and is bootstrapped with the `kkdt.sample.webboot.http.SampleConsole` Spring Boot Application as a parent context. The bootstrapping occurs in `SampleWebBoot` application entry.

The Swing application container has the same name as the web container but is in a different package `kkdt.sample.webboot.client.SampleConsole` (note 'client'). This container is tagged by an interface `ClientApplication`.

> Original project exported from a personal subversion server into a git repository, and pushed to Github.