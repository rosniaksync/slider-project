package com.gabriel.sliderplaces.service;

import com.gabriel.sliderplaces.dto.DestinoDto;
import lombok.RequiredArgsConstructor;
import com.gabriel.sliderplaces.mapper.DestinoMapper;
import org.springframework.stereotype.Service;
import com.gabriel.sliderplaces.repository.DestinoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinoService {

    private final DestinoRepository repository;
    private final DestinoMapper mapper;

    public List<DestinoDto> listarDestinos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}