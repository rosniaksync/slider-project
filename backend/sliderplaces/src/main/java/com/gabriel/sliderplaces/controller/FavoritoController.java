package com.gabriel.sliderplaces.controller;

import com.gabriel.sliderplaces.dto.DestinoDto;
import com.gabriel.sliderplaces.model.UsuarioEntity;
import com.gabriel.sliderplaces.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorito")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService service;

    @PostMapping("{id}")
    public ResponseEntity<Void> favoritarDestino(@AuthenticationPrincipal UsuarioEntity usuario, @PathVariable Long id) {
        service.favoritarDestino(usuario, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<DestinoDto>> listarFavoritos(@AuthenticationPrincipal UsuarioEntity usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarFavoritos(usuario));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> desfavoritarDestino(@AuthenticationPrincipal UsuarioEntity usuario, @PathVariable Long id) {
        service.desfavoritarDestino(usuario, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
