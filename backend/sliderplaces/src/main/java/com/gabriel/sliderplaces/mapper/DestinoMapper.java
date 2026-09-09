package com.gabriel.sliderplaces.mapper;

import com.gabriel.sliderplaces.dto.DestinoDto;
import com.gabriel.sliderplaces.model.DestinoEntity;
import org.springframework.stereotype.Component;

@Component
public class DestinoMapper {

    public DestinoDto toDto(DestinoEntity destino) {
        return new DestinoDto(
                destino.getId(),
                destino.getNome(),
                destino.getDescricao(),
                destino.getImagem()
        );
    }
}