package com.cafemanager.cafemanager.api.controller;

import com.cafemanager.cafemanager.api.request.LoginRequestDTO;
import com.cafemanager.cafemanager.api.response.LoginResponseDTO;
import com.cafemanager.cafemanager.application.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO dto
    ) {

        return authService.autenticar(dto);
    }

}
