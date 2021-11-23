# vs_microservices
Abgabe für das Verteilte Systeme Labor


<h4>Docker:</h4>

-   build jar file:
    ```console
    cd ./product-service 
    ./gradlew build
    cd .././search-service 
    ./gradlew build
    cd ../
    ```
-   test jar file:
    ```console
    java -jar ./product-service/build/libs/product-service.jar
    java -jar ./search-service/build/libs/search-service.jar
    ```
-   build image:
    ```console
    cd ./product-service
    docker build -t joma1021/product-service-docker .
    cd .././search-service
    docker build -t unsi1012/search-service-docker .
    ```
-   docker compose:
    ```console
    cd ../
    docker-compose up -d

<h4>Wichtig!</h4>

-   Gegen DB verbinden und sql auführen:
    ```sql
        CREATE DATABASE IF NOT EXISTS `product`;   
        CREATE USER 'user'@'%' IDENTIFIED BY 'password';
        GRANT ALL ON *.* TO 'user'@'%';
    ```