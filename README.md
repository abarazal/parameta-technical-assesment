# Parameta Technical Assesment

Proyecto que consta de 2 servicios Spring Boot conectados entre sí para guardar los datos de un empleado en una base de datos MySQL.

- [parameta-api-rest](/parameta-api-rest) es el servicio que expone una API REST que recibe los datos del empleado, verifica las restricciones y llama al servicio SOAP que efectivamente persistirá los datos.

- [parameta-soap-web-service](/parameta-soap-web-service) es el servicio web que expone un endpoint que recibe los mensajes SOAP con los datos del empleado y luego lo guarda en la base de datos. 
