package com.gabriel.sliderplaces.controller;

import com.gabriel.sliderplaces.model.FavoritoEntity;
import com.gabriel.sliderplaces.repository.FavoritoRepository;
import com.gabriel.sliderplaces.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favorito")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService service;

    @PostMapping("{id}")
    public ResponseEntity<Void> favoritarDestino(Long id) {
        FavoritoEntity favorito = 
    }

}
