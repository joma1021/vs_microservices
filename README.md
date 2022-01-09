# vs_microservices
Abgabe für das Verteilte Systeme Labor

<h2>Docker:</h2>

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


<h2> Gegen DB Verbinden</h2>

-   Gegen DB verbinden und SQL-Befehl ausführen:
    ```console
    mysql --host=127.0.0.1 --port=3306 -u root -p
    ```
    Password: c8de110f37300a53a971749

-   SQl-Bsp zum DB anlegen:   
    ```sql
        CREATE DATABASE IF NOT EXISTS `product`;   
        CREATE USER 'user'@'%' IDENTIFIED BY 'password';
        GRANT ALL ON *.* TO 'user'@'%';
    ```


<h2>Kubernetes</h2>

<h3>Create YAML-Files</h3>

-   install: https://kubernetes.io/docs/tasks/configure-pod-container/translate-compose-kubernetes/
-   exec: 
    ```console
    kompose convert
    ```
-   add "imagePullPolicy: Never" to all -service.yaml files. Example:
    ```yaml
    containers:
        - env:
            - name: MYSQL_HOST
              value: web-shop-db-image
          image: product-service
          imagePullPolicy: Never
          name: product-service
    ```

<h3>Deployment</h3>
    
-   start minikube:
    ```console
    minikube start
    ```
-   map minikube to docker-env:
    ```console
    minikube docker-env
    ```
    ```console
    & minikube -p minikube docker-env | Invoke-Expression
    ```
    oder

    ```console
    eval $(minikube -p minikube docker-env)
    ```
-   docker build:
    ```console
    docker-compose build
    ```
-   kubectl apply:
    ```console
    kubectl apply -f product-service-service.yaml,search-service-service.yaml,web-shop-db-image-service.yaml,product-service-deployment.yaml,search-service-deployment.yaml,web-shop-db-image-deployment.yaml,web-shop-db-image-claim0-persistentvolumeclaim.yaml,apache-development.yaml,apache-service.yaml
    ```    
-   open apache page:
    ```console
    minikube service apache
    ```
 -  remove kubernetes deployments:
    ```console
    ./kubernetes-remove.sh
    ```   
-   delete minikube:
    ```console
    minikube delete --all
    ```

-   open minikube dashboard:
     ```console
    minikube dashboard
    ```

-   open tunnel + show external ip
     ```console
    minikube tunnel -c
    kubectl get svc
    ```
