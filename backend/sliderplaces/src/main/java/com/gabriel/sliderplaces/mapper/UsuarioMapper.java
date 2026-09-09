package com.gabriel.sliderplaces.mapper;

import com.gabriel.sliderplaces.dto.UsuarioDto;
import com.gabriel.sliderplaces.model.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDto toDto(UsuarioEntity usuario) {
        return new UsuarioDto(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }
}