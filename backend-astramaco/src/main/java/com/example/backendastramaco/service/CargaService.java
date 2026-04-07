package com.example.backendastramaco.service;

import com.example.backendastramaco.model.Carga;
import com.example.backendastramaco.model.Transportista;
import com.example.backendastramaco.model.enums.TipoTransporte;
import com.example.backendastramaco.repository.CargaRepository;
import com.example.backendastramaco.repository.TransportistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargaService {

    private final CargaRepository cargaRepository;
    private final TransportistaRepository transportistaRepository;

    public Carga registrarCarga(Long transportistaId, Carga carga) {

        Transportista t = transportistaRepository.findById(transportistaId)
                .orElseThrow(() -> new RuntimeException("Transportista no existe"));

        if (t.getTipoTransporte() != TipoTransporte.CAMIONERO) {
            throw new RuntimeException("Solo camioneros pueden registrar carga");
        }

        carga.setTransportista(t);

        return cargaRepository.save(carga);
    }

    public List<Carga> obtenerPorTransportista(Long id) {
        return cargaRepository.findAll()
                .stream()
                .filter(c -> c.getTransportista().getId().equals(id))
                .toList();
    }
}