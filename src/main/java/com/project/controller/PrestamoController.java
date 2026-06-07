package com.project.controller;

import com.project.entity.Prestamo;
import com.project.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoService.obtenerTodos();
    }

    @GetMapping("/activos")
    public List<Prestamo> obtenerPrestamosActivos() {
        return prestamoService.obtenerPrestamosActivos();
    }

    @PostMapping
    public Prestamo crearPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.crearPrestamo(prestamo);
    }

    @PutMapping("/{id}/devolver")
    public Prestamo devolverPrestamo(@PathVariable Long id) {
        return prestamoService.devolverPrestamo(id);
    }
    
    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable Long id) {
        prestamoService.eliminar(id);
    }

    
}
