# Spring Boot Sample

## Frameworks Used
- Spring Boot
- JPA
- Swagger Spring MVC
- Swagger UI
- JUnit
- Mockito
- Cucumber-JVM
- Rest Assured
- Rest Assured JSON Schema Validator

This project uses yaml for configuration properties and has 2 spring profiles:
- dev: runs tests against the local application instance
- ci:  runs tests against the deployed application instance in ci environment

## Building the application:
   Go to the application root folder (i.e, folder containing the build.gradle file), lets call it "appBase"
   and the the following command.
   $appBase> gradle clean build

## Test report:
   The test report html is located at $appBase/build/reports/tests/index.html

## Running the application:
   After Step 1, you should have an executable jar at $appBase/build/libs/$jarname.jar folder.
   Go to the $appBase folder, and run the following command:
   $appBase> java -jar build/libs/jarname.jar

## Diagnostic Endpoints:
   The following diagnostic endpoints are available:
   /errors, /environment, /health, /beans, /info, /metrics, /trace, /configprops, and /dump

5. RestAPI Documentation:
    Swagger UI can be accessed at: http://localhost:8080/webjars/swagger-ui/2.0.24/index.html

    You will have to provide the admin apps swagger documentation URL, which is, localhost:8080/api-docs
