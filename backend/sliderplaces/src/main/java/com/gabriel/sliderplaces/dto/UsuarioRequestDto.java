package com.gabriel.sliderplaces.dto;

import jakarta.validation.constraints.Email;

public record UsuarioRequestDto(

        @Email
        String email,

        String senha) {}