package com.example.backendastramaco.service;

import com.example.backendastramaco.dto.PedidoRequestDTO;
import com.example.backendastramaco.model.Carga;
import com.example.backendastramaco.model.Pedido;
import com.example.backendastramaco.model.Transportista;

import com.example.backendastramaco.model.enums.TipoTransporte;
import com.example.backendastramaco.repository.CargaRepository;
import com.example.backendastramaco.repository.PedidoRepository;
import com.example.backendastramaco.repository.TransportistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final TransportistaRepository transportistaRepository;
    private final CargaRepository cargaRepository;

    public Pedido crearPedido(PedidoRequestDTO dto) {

        Transportista t = transportistaRepository.findById(dto.getTransportistaId())
                .orElseThrow();

        if (!t.getTipoTransporte().equals(dto.getTipoTransporte())) {
            throw new RuntimeException("Tipo incorrecto");
        }

        // 🔥 CAMIONERO
        if (t.getTipoTransporte() == TipoTransporte.CAMIONERO) {

            Carga carga = cargaRepository
                    .findByTransportistaIdAndTipoMaterial(t.getId(), dto.getMaterial())
                    .orElseThrow();

            if (carga.getCantidadDisponible() < dto.getCantidad()) {
                throw new RuntimeException("Sin stock");
            }

            carga.setCantidadDisponible(
                    carga.getCantidadDisponible() - dto.getCantidad()
            );

            cargaRepository.save(carga);
        }

        Pedido pedido = Pedido.builder()
                .clienteNombre(dto.getClienteNombre())
                .clienteTelefono(dto.getClienteTelefono())
                .direccionEnvio(dto.getDireccionEnvio())
                .tipoTransporte(dto.getTipoTransporte())
                .material(dto.getMaterial())
                .cantidad(dto.getCantidad())
                .montoTotal(dto.getMontoTotal())
                .adelanto(dto.getAdelanto())
                .piso(dto.getPiso())
                .horaEnvio(dto.getHoraEnvio())
                .transportista(t)
                .codigoVerificacion("1234")
                .build();

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }
}