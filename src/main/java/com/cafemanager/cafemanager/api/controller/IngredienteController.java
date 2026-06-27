package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.IngredienteRequestDTO;
import com.cafemanager.cafemanager.domain.entity.Ingrediente;
import com.cafemanager.cafemanager.application.service.IngredienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {


    private final IngredienteService ingredienteService;


    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }


    @GetMapping
    public List<Ingrediente> listar() {
        return ingredienteService.listarTodos();
    }


    @PostMapping
    public Ingrediente crear(
            @Valid @RequestBody IngredienteRequestDTO ingredienteDTO) {
        return ingredienteService.guardar(ingredienteDTO);
    }


    @GetMapping("/{id}")
    public Ingrediente buscar(@PathVariable Long id) {
        return ingredienteService.buscarPorId(id);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ingredienteService.eliminar(id);
    }
}
