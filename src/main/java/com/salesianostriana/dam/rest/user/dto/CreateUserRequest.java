package com.salesianostriana.dam.rest.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String username;
    @NotEmpty(message = "La contraseña no puede estar en blanco")
    @Length(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    @NotEmpty(message = "La contraseña no puede estar en blanco")
    @Length(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String verifyPassword;
    @Email(message = "El email debe tener un formato correcto")
    private String email;
    private String avatar;
    private String fullName;

}
