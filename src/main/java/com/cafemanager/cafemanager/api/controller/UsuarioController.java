package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.UsuarioCreateRequestDTO;
import com.cafemanager.cafemanager.api.request.UsuarioUpdateRequestDTO;
import com.cafemanager.cafemanager.api.response.UsuarioResponseDTO;
import com.cafemanager.cafemanager.application.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(
            UsuarioService usuarioService
    ) {
        this.usuarioService = usuarioService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Listar usuarios",
            description = "Obtiene todos los usuarios registrados. Solo disponible para ADMIN."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios encontrados"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acceso denegado"
            )
    })
    @GetMapping
    public List<UsuarioResponseDTO> listar() {

        return usuarioService.listar();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Buscar usuario por ID",
            description = "Obtiene un usuario específico."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado"
            )
    })
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(
            @PathVariable Long id
    ) {

        return usuarioService.buscarPorId(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Crear usuario",
            description = "Crea un nuevo usuario con contraseña encriptada."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario creado correctamente"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Email ya registrado"
            )
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(
            @Valid @RequestBody UsuarioCreateRequestDTO dto
    ) {

        UsuarioResponseDTO respuesta =
                usuarioService.crear(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Actualizar usuario",
            description = "Actualiza datos generales del usuario."
    )
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateRequestDTO dto
    ) {

        UsuarioResponseDTO respuesta =
                usuarioService.actualizar(id, dto);

        return ResponseEntity.ok(respuesta);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Desactivar usuario",
            description = "Realiza una baja lógica del usuario."
    )
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> desactivar(
            @PathVariable Long id
    ) {

        usuarioService.desactivar(id);

        return ResponseEntity.noContent().build();
    }

}