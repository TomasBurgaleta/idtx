#   Prueba de concepto para idtx


Tecnologias usadas para esta prueba:

1. Spring boot
2. Spring rest y manejo de excepciones
3. Jpa
4. Clean code
5. Maven usando varios moulos
6. Arquitectura Hexgonal con capas independiestes
7. Pruebas unitarias en clases importantes
8. Test funcionales end 2 end (cucumber)
9. BBDD H2 
10. Uso de git


Modo de ejecucion de la app:

Entrar en la consola y ejecutar este comando para instalar y ejecutar 

`./mvnw install && ./mvnw spring-boot:run -pl boot`

Se puede ejecutar el servicio rest de varias maneras:

la mas básica es en un navegador o en postman con una llamada de este tipo:

http://localhost:8080/api/price?currentDate=2020-06-16T01:30:00&product=35455&brand=1

Tambien se puede entrar en la consola swagger y ejecutar las llamadas

 http://localhost:8080/swagger-ui/index.html#/

tambien se puede ver un reporte de cucumber

`[ruta raiz proyecto]/boot/target/cucumber-reports/Cucumber.html`

 añadimos CI  