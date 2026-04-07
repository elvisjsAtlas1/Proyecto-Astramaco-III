package com.example.backendastramaco.dto;

import com.example.backendastramaco.model.enums.EstadoPedido;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoResponseDTO {

    private Long id;
    private String clienteNombre;
    private EstadoPedido estado;
    private String codigoVerificacion;
}