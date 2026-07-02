// Archivo: src/main/java/com/example/demo/api/Curso.java
package com.example.demo.api;
 
// record es una forma concisa de crear una clase de datos inmutable en Java 16+
// Genera automáticamente constructor, getters (id(), nomcurso(), activo()),
// equals(), hashCode() y toString()
record Curso(int id, String nomcurso, Boolean activo) {
 
    // Uso: new Curso(1, "JavaScript Avanzado", true)
    // Acceso: curso.id()  |  curso.nomcurso()  |  curso.activo()
}
