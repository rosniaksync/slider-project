package com.gabriel.sliderplaces.service;

import com.gabriel.sliderplaces.dto.UsuarioDto;
import com.gabriel.sliderplaces.mapper.UsuarioMapper;
import com.gabriel.sliderplaces.model.UsuarioEntity;
import com.gabriel.sliderplaces.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper mapper;

    public UsuarioDto registrar(UsuarioDto dto) {

        UsuarioEntity usuario;

        if(repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Esse email já existe!");
        } else {
            usuario = UsuarioEntity.builder()
                    .nome(dto.nome())
                    .email(dto.email())
                    .senha(passwordEncoder.encode(dto.senha()))
                    .build();

           UsuarioEntity user = repository.save(usuario);

           return mapper.toDto(user);
        }
    }
}