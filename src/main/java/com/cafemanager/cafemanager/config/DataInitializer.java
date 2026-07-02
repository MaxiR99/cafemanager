package com.cafemanager.cafemanager.config;

import com.cafemanager.cafemanager.domain.entity.Usuario;
import com.cafemanager.cafemanager.domain.enums.Rol;
import com.cafemanager.cafemanager.domain.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public DataInitializer(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {


        if(usuarioRepository.count() == 0){


            Usuario admin = new Usuario();

            admin.setNombre("Administrador");
            admin.setEmail("admin@cafemanager.com");
            admin.setPassword(
                    passwordEncoder.encode("Admin123")
            );
            admin.setRol(Rol.ADMIN);
            admin.setActivo(true);


            usuarioRepository.save(admin);


            System.out.println(
                    "Usuario administrador creado correctamente"
            );
        }

    }
}