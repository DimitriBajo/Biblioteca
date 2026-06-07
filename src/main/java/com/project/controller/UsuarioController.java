package com.project.controller;

import com.project.entity.Usuario;
import com.project.service.UsuarioService;
import com.project.entity.Prestamo;
import com.project.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PrestamoService prestamoService;

    public UsuarioController(UsuarioService usuarioService, PrestamoService prestamoService) {
        this.usuarioService = usuarioService;
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @GetMapping("/{id}/prestamos")
    public List<Prestamo> obtenerPrestamosPorUsuario(@PathVariable Long id) {
        return prestamoService.obtenerPrestamosPorUsuario(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
