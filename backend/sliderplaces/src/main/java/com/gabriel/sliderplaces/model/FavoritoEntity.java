package com.gabriel.sliderplaces.model;

import jakarta.persistence.*;
import lombok.*;
import com.gabriel.sliderplaces.model.UsuarioEntity;

@Entity
@Table(name = "favoritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "destino_id")
    private DestinoEntity destino;
}