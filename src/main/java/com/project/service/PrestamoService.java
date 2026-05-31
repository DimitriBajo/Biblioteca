package com.project.service;

import com.project.entity.Libro;
import com.project.entity.Prestamo;
import com.project.repository.LibroRepository;
import com.project.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,
                           LibroRepository libroRepository) {

        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public Prestamo crearPrestamo(Prestamo prestamo) {

        Libro libro = prestamo.getLibro();

        if (!libro.getDisponible()) {
            throw new RuntimeException("El libro ya está prestado");
        }

        libro.prestar();

        libroRepository.save(libro);

        return prestamoRepository.save(prestamo);
    }

    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }
}