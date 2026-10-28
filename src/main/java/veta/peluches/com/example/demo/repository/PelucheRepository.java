package veta.peluches.com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import veta.peluches.com.example.demo.model.Peluche;

public interface PelucheRepository extends JpaRepository<Peluche, Long> {

    List<Peluche> findByCategoria(String categoria);
}
