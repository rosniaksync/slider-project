package com.gabriel.sliderplaces.controller;

import com.gabriel.sliderplaces.dto.DestinoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabriel.sliderplaces.service.DestinoService;

import java.util.List;

@RestController
@RequestMapping("/destino")
@RequiredArgsConstructor
public class DestinoController {

    private final DestinoService service;

    @GetMapping
    public ResponseEntity<List<DestinoDto>> listarDestinos() {
        return ResponseEntity.ok(service.listarDestinos());
    }
}