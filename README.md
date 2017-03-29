# sample-webboot

This sample includes a very basic Swing application defined by a Spring XML context file. Within that context, is a shared model `CounterModel` that just increment a counter and publishes it to the Swing GUI. This application is bootstrapped within a Spring Boot `ApplicationContext` and the same model is used in an exposed RESTful endpoint (GET method).

The Swing application is a `JFrame` and the RESTful endpoint is at `http://localhost:8080/counter`. Navigating a browser to the URL (and refreshing) will get the current counter value that is also displayed in the Swing UI.

This is a prototype for integrating a Spring-based Swing application and a Spring Boot web application while exploring a proof-of-concept for migrating the thick-client into a web-based application. With the assumption that the legacy Swing application is Spring-heavy, the overall approach for migration into a thin-client is combine both Swing and Spring Boot `ApplicationContext`s into a single context sharing appropriate components/beans (data providers, models, etc) that can be utilized with the web components (i.e. controllers, views, endpoints, etc), and iteratively deprecate legacy Swing views.

## Quick Start

Build the Jar and run it

```javascript
gradle cleanAll assemble
java -jar build/libs/sample-webboot-<version>.jar
```

For convenience, the runnable jar is also checked into the `run` directory. Double-click it or run it via Java from command line using the snippet above.

## Version 0.1 

Goals/questions explored

1. Spring Boot API for bootstrapping and custom application initialization
2. Can a thick client Swing application coexist with Spring Boot web application container?
3. Can both Swing and Web application context share beans?

## Version 0.2

Goals/questions explored

1. Integrate Spring Security such that Swing authenticates and any access to web resources prior to logging into the Swing client is immediately returned as an UNAUTHORIZED
2. Explore Spring Boot opinionated Spring Security hookups

> Original project exported from a personal subversion server into a git repository, and pushed to Github.