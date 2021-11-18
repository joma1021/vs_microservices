<h3>Basic Information</h3>
- Build with Gradle 
- Preferred IDE: IntelliJ
- Legacy Docker DB needs to be running
- Test REST Requests in ./example

<h4>Docker:</h4>

-   build jar file:
    ```console
    ./gradlew build
    ```
-   test jar file:
    ```console
    java -jar build/libs/product-service.jar
    ```
-   build image:
    ```console
    docker build -t joma1021/product-service-docker .
    ```
-   docker compose:
    ```console
    product-service>docker-compose up -d
    ```

