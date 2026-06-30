package com.cafemanager.cafemanager.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @Pattern(
            regexp = "^[0-9+\\-() ]*$",
            message = "El teléfono tiene un formato inválido"
    )
    private String telefono;

    @Email(message = "Email inválido")
    private String email;

    @Size(max = 200)
    private String direccion;

}