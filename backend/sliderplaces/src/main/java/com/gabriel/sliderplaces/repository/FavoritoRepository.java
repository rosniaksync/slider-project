package com.gabriel.sliderplaces.repository;

import com.gabriel.sliderplaces.model.DestinoEntity;
import com.gabriel.sliderplaces.model.FavoritoEntity;
import com.gabriel.sliderplaces.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<FavoritoEntity, Long> {

    boolean existsByUsuarioAndDestino(UsuarioEntity usuario, DestinoEntity destino);

    List<FavoritoEntity> findAllByUsuario(UsuarioEntity usuario);

    Optional<FavoritoEntity> findByUsuarioAndDestino(UsuarioEntity usuario, DestinoEntity destino);
}