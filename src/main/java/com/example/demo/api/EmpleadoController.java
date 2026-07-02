// Archivo: src/main/java/com/example/demo/api/EmpleadoController.java
package com.example.demo.api;
 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
 
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpleadoController {
 
    // Lista de empleados en memoria (simula tabla de RRHH)
    private final List<Empleado> empleados = List.of(
        new Empleado(1, "Ana", "TI", 4500),
        new Empleado(2, "Luis", "RRHH", 3800),
        new Empleado(3, "María", "TI", 5200)
    );
 
    // GET /api/empleados → retorna todos los empleados en JSON
    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados() {
        return empleados;
    }
 
    // GET /api/empleados/{id} → busca por ID, retorna 200 o 404
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable int id) {
        return empleados.stream()
            .filter(e -> e.id() == id)
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
