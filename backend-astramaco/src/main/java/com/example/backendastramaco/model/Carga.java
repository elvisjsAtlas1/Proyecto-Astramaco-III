package com.example.backendastramaco.model;


import com.example.backendastramaco.model.enums.TipoMaterial;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cargas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transportista_id")
    private Transportista transportista;

    @Enumerated(EnumType.STRING)
    private TipoMaterial tipoMaterial;

    private Double cantidadDisponible;
}