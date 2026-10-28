package veta.peluches.com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import veta.peluches.com.example.demo.model.Peluche;
import veta.peluches.com.example.demo.service.PelucheService;

@RestController
@RequestMapping("/api/peluches")
@CrossOrigin(origins = "http://localhost:5173")
public class PelucheController {

    private final PelucheService service;

    public PelucheController(PelucheService service) {
        this.service = service;
    }

    @GetMapping
    public List<Peluche> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peluche> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Peluche crear(@Valid @RequestBody Peluche peluche) {
        return service.guardar(peluche);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peluche> actualizar(@PathVariable Long id, @Valid @RequestBody Peluche peluche) {
        return service.actualizar(id, peluche)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint extra: buscar por categoría -> GET /api/peluches/categoria?nombre=Osos
    @GetMapping("/categoria")
    public List<Peluche> porCategoria(@RequestParam String nombre) {
        return service.buscarPorCategoria(nombre);
    }
}
