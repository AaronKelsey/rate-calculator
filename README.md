
## Prerequisites

You need to install the following tools

-   JDK 1.8+
-   Maven 3.1+

## How to build the application:

-   Unzip the supplied zip file
-   From the command line navigate to the project root location and run the command below.

```
mvn clean install
```

## How to run the application

-   After building the code, run the below command from the project root directory.
```
java -jar target/rate-calculator.jar 1000
```

## How to run the tests
- To run the unit tests, run the below command from the project root directory.
```
mvn test
```

## Improvements
- Use BigDecimal for greater precision.