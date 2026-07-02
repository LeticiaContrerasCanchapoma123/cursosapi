// Archivo: src/main/java/com/example/demo/api/CursoController.java
package com.example.demo.api;
 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
 
// @RestController: esta clase es una API REST.
// Todos sus métodos retornan datos (JSON o texto), NO vistas HTML.
@RestController
 
// @RequestMapping: todas las rutas de esta clase empiezan con /api
@RequestMapping("/api")
 
// @CrossOrigin: permite que React en localhost:5173 consuma esta API.
// Sin esto, el navegador bloquea las solicitudes por política CORS.
@CrossOrigin(origins = "http://localhost:5173")

public class CursoController {
        // Lista de cursos en memoria (simula una base de datos)
    private final List<Curso> cursos = List.of(
        new Curso(1, "JavaScript Avanzado", true),
        new Curso(2, "Base de Datos", true),
        new Curso(3, "Arquitectura de Computadoras", false)
    );
 
    // ----------------------------------------------------------------
    // ENDPOINT 1: GET /api/hola
    // Prueba básica: retorna un String directamente.
    // El navegador muestra el texto tal cual.
    // ----------------------------------------------------------------
    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo desde Spring Boot";
    }
 
    // ----------------------------------------------------------------
    // ENDPOINT 2: GET /api/cursos
    // Retorna la lista completa de cursos.
    // Spring Boot convierte automáticamente List<Curso> a JSON.
    // ----------------------------------------------------------------
    @GetMapping("/cursos")
    public List<Curso> listarCursos() {
        return cursos;  // Spring convierte esto a JSON automáticamente
    }
 
    // ----------------------------------------------------------------
    // ENDPOINT 3: GET /api/cursos/{id}
    // @PathVariable: el ID viene como parte de la URL.
    // Ejemplo: GET /api/cursos/1 retorna el curso con id=1.
    // ResponseEntity permite controlar el código HTTP:
    //   - 200 OK si el curso existe
    //   - 404 Not Found si no existe
    // ----------------------------------------------------------------
    @GetMapping("/cursos/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable int id) {
        return cursos.stream()               // Recorre la lista
            .filter(e -> e.id() == id)       // Filtra por ID
            .findFirst()                     // Toma el primero que coincida
            .map(ResponseEntity::ok)         // Si existe: 200 OK + datos
            .orElse(ResponseEntity.notFound().build()); // Si no: 404
    }
 
    // ----------------------------------------------------------------
    // ENDPOINT 4: GET /api/cursos/filtrar?activo=true
    // @RequestParam: el parámetro viene después del signo ?
    // Diferencia con @PathVariable:
    //   @PathVariable: /cursos/1       → busca por ID (recurso específico)
    //   @RequestParam: /cursos?activo=true → filtra (búsqueda con criterios)
    // ----------------------------------------------------------------
    @GetMapping("/cursos/filtrar")
    public List<Curso> filtrarPorActivo(@RequestParam boolean activo) {
        return cursos.stream()
            .filter(c -> c.activo() == activo)  // Filtra por estado activo
            .toList();
    }

}
