## Ding Assessment

This is built using gradle on java 21.

Run `docker compose up -d ` to get the postgres db running.

Run `./gradlew clean build ` to build the project. Then, run the application using your favourite IDE.

You may access [api documentation](http://localhost:8080/swagger-ui/index.html#/) to know more.
You may use SwaggerHub (or any other API testing tool) to test.

A sample request body for testing POST and PUT

    {
    "firstName": "JohDingn",
    "lastName": "Doe",
    "personalEmail": "john.doe@example.com",
    "officeEmail": "john.doe@company.com"
    }

Have fun =)

***Note: Test with Customers endpoints!! Product endpoints are not implemented.***
