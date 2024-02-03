# Parameta Technical Assessment

Proyecto que consta de 2 servicios Spring Boot conectados entre sí para guardar los datos de un empleado en una base de datos MySQL.

- [parameta-api-rest](/parameta_api_rest) es el servicio que expone una API REST que recibe los datos del empleado, verifica las restricciones y llama al servicio SOAP que efectivamente persistirá los datos.

- [parameta-soap-web-service](/parameta_soap_web_service) es el servicio web que expone un endpoint que recibe los mensajes SOAP con los datos del empleado y luego lo guarda en la base de datos.

## Pre-requisitos 📋

Se necesita **Maven 3.9.6** y **JDK 17**.

## Construcción 🔧

```
cd <ruta_a_parameta_api_rest>
mvn clean package

cd <ruta_a_parameta_soap_web_service>
mvn clean package
```

## Despliegue 📦

```
java -jar <ruta_a_parameta_api_rest>/target/parameta-soap-web-service-1.0.0.jar
java -jar <ruta_a_parameta_soap_web_service>/target/parameta-soap-web-service-1.0.0.jar 
```

## API :paperclip:

Se proveen las [Postman collections](/postman_collections/) para probar la API REST y el servicio SOAP.


## Base de datos 🛢️

Para crear la base de datos y la tabla de empleado se puede utilizar [create_employee.sql](/scripts/create_employee.sql): 
