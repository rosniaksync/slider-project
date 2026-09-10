package com.gabriel.sliderplaces.service;

import com.gabriel.sliderplaces.dto.DestinoDto;
import com.gabriel.sliderplaces.mapper.DestinoMapper;
import com.gabriel.sliderplaces.model.DestinoEntity;
import com.gabriel.sliderplaces.model.FavoritoEntity;
import com.gabriel.sliderplaces.model.UsuarioEntity;
import com.gabriel.sliderplaces.repository.DestinoRepository;
import com.gabriel.sliderplaces.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository repository;
    private final DestinoRepository destinoRepository;
    private final DestinoMapper mapper;

    public void favoritarDestino(UsuarioEntity usuario, Long destinoId) {

        DestinoEntity destino = destinoRepository.findById(destinoId)
                .orElseThrow(() -> new IllegalArgumentException("Destino não encontrado"));

        if(repository.existsByUsuarioAndDestino(usuario, destino)) {
            throw new IllegalArgumentException("Você já favoritou esse destino");
        } else {
            FavoritoEntity favorito = FavoritoEntity
                    .builder()
                    .usuario(usuario)
                    .destino(destino)
                    .build();

            repository.save(favorito);
        }
    }

    public void desfavoritarDestino(UsuarioEntity usuario, Long destinoId) {
        DestinoEntity destino = destinoRepository.findById(destinoId)
                .orElseThrow(() -> new IllegalArgumentException("Destino não encontrado"));

        FavoritoEntity favorito = repository.findByUsuarioAndDestino(usuario, destino)
                .orElseThrow(() -> new IllegalArgumentException("Você ainda não favoritou esse destino"));

        repository.delete(favorito);
    }

    public List<DestinoDto> listarFavoritos(UsuarioEntity usuario) {
        return repository.findAllByUsuario(usuario)
                .stream()
                .map(favorito -> mapper.toDto(favorito.getDestino()))
                .toList();
    }
}