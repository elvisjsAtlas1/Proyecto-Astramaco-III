package com.example.backendastramaco.controller;

import com.example.backendastramaco.model.Carga;
import com.example.backendastramaco.service.CargaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargas")
@RequiredArgsConstructor
public class CargaController {

    private final CargaService service;

    @PostMapping("/{transportistaId}")
    public Carga registrar(
            @PathVariable Long transportistaId,
            @RequestBody Carga carga) {

        return service.registrarCarga(transportistaId, carga);
    }

    @GetMapping("/{transportistaId}")
    public List<Carga> listar(@PathVariable Long transportistaId) {
        return service.obtenerPorTransportista(transportistaId);
    }
}