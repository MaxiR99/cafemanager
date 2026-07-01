package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.UsuarioCreateRequestDTO;
import com.cafemanager.cafemanager.api.request.UsuarioUpdateRequestDTO;
import com.cafemanager.cafemanager.api.response.UsuarioResponseDTO;
import com.cafemanager.cafemanager.application.mapper.UsuarioMapper;
import com.cafemanager.cafemanager.domain.entity.Usuario;
import com.cafemanager.cafemanager.domain.repository.UsuarioRepository;
import com.cafemanager.cafemanager.exception.RecursoDuplicadoException;
import com.cafemanager.cafemanager.exception.RecursoNoEncontradoException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }




    public List<UsuarioResponseDTO> listar() {

        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .toList();

    }

    public UsuarioResponseDTO buscarPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Usuario no encontrado"
                        )
                );

        return UsuarioMapper.toResponse(usuario);
    }


    public UsuarioResponseDTO crear(UsuarioCreateRequestDTO dto) {

        if (usuarioRepository.existsByEmailIgnoreCase(dto.getEmail())) {

            throw new RecursoDuplicadoException(
                    "Ya existe un usuario con ese email"
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        usuario.setRol(dto.getRol());
        usuario.setActivo(true);

        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }

    public UsuarioResponseDTO actualizar(
            Long id,
            UsuarioUpdateRequestDTO dto
    ) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Usuario no encontrado"
                        )
                );

        if (!usuario.getEmail().equalsIgnoreCase(dto.getEmail())
                && usuarioRepository.existsByEmailIgnoreCase(dto.getEmail())) {

            throw new RecursoDuplicadoException(
                    "Ya existe un usuario con ese email"
            );
        }

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());

        usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuario);
    }

    public void desactivar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Usuario no encontrado"
                        )
                );

        usuario.setActivo(false);

        usuarioRepository.save(usuario);

    }

}