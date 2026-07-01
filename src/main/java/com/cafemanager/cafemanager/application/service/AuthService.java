package com.cafemanager.cafemanager.application.service;

import com.cafemanager.cafemanager.api.request.LoginRequestDTO;
import com.cafemanager.cafemanager.api.response.LoginResponseDTO;
import com.cafemanager.cafemanager.domain.entity.Usuario;
import com.cafemanager.cafemanager.domain.repository.UsuarioRepository;
import com.cafemanager.cafemanager.exception.ReglaNegocioException;
import com.cafemanager.cafemanager.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO autenticar(LoginRequestDTO dto) {

        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new ReglaNegocioException(
                                "Email o contraseña incorrectos"
                        )
                );

        if (!passwordEncoder.matches(
                dto.getPassword(),
                usuario.getPassword())) {

            throw new ReglaNegocioException(
                    "Email o contraseña incorrectos"
            );
        }

        if (!usuario.getActivo()) {

            throw new ReglaNegocioException(
                    "El usuario está inactivo"
            );
        }

        String token = jwtService.generateToken(usuario);

        return new LoginResponseDTO(token);
    }

}