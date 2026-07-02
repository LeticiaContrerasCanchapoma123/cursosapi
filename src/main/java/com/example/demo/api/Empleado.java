// Archivo: src/main/java/com/example/demo/api/Empleado.java
package com.example.demo.api;
 
// record Empleado: id, nombre, departamento y salario
// Acceso: empleado.id()  |  empleado.nombre()  |  empleado.departamento()  |  empleado.salario()
record Empleado(int id, String nombre, String departamento, double salario) {}

