package veta.peluches.com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import veta.peluches.com.example.demo.model.Peluche;
import veta.peluches.com.example.demo.repository.PelucheRepository;

@Service
public class PelucheService {

    private final PelucheRepository repository;

    public PelucheService(PelucheRepository repository) {
        this.repository = repository;
    }

    public List<Peluche> listar() {
        return repository.findAll();
    }

    public Optional<Peluche> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Peluche> buscarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    public Peluche guardar(Peluche peluche) {
        return repository.save(peluche);
    }

    public Optional<Peluche> actualizar(Long id, Peluche datos) {
        Optional<Peluche> existente = repository.findById(id);
        if (existente.isEmpty()) {
            return Optional.empty();
        }
        Peluche p = existente.get();
        p.setNombre(datos.getNombre());
        p.setDescripcion(datos.getDescripcion());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());
        return Optional.of(repository.save(p));
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    // Regla de negocio: no se puede vender más de lo que hay en stock
    public String descontarStock(Long id, int cantidad) {
        Optional<Peluche> existente = repository.findById(id);
        if (existente.isEmpty()) {
            return "No existe el peluche";
        }
        Peluche p = existente.get();
        if (cantidad <= 0) {
            return "La cantidad debe ser mayor a 0";
        }
        if (p.getStock() < cantidad) {
            return "Stock insuficiente. Disponible: " + p.getStock();
        }
        p.setStock(p.getStock() - cantidad);
        repository.save(p);
        return "OK";
    }
}
