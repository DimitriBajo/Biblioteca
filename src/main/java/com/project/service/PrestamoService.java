package com.project.service;

import com.project.entity.Libro;
import com.project.entity.Prestamo;
import com.project.repository.LibroRepository;
import com.project.repository.PrestamoRepository;
import com.project.dto.CrearPrestamoDTO;
import com.project.entity.Usuario;
import com.project.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.time.LocalDate;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository,
            LibroRepository libroRepository, UsuarioRepository usuarioRepository) {

        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        return prestamoRepository.findByFechaDevolucionIsNull();
    }

    public List<Prestamo> obtenerPrestamosPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    public Prestamo crearPrestamo(CrearPrestamoDTO crearPrestamoDTO) {

        Usuario usuario = usuarioRepository
                .findById(crearPrestamoDTO.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        Libro libro = libroRepository
                .findById(crearPrestamoDTO.getLibroId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado"));

        if (!libro.getDisponible()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El libro ya esta prestado");
        }

        Prestamo prestamo = new Prestamo();

        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(LocalDate.now());

        libro.prestar();

        libroRepository.save(libro);

        return prestamoRepository.save(prestamo);
    }

    public Prestamo devolverPrestamo(Long id) {

        Prestamo prestamo = prestamoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestamo no encontrado"));

        Libro libro = prestamo.getLibro();

        libro.devolver();

        prestamo.setFechaDevolucion(LocalDate.now());

        libroRepository.save(libro);

        return prestamoRepository.save(prestamo);
    }

    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }

}