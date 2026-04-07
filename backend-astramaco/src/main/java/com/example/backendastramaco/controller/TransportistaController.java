package com.example.backendastramaco.controller;

import com.example.backendastramaco.dto.TransportistaRequestDTO;
import com.example.backendastramaco.model.Transportista;
import com.example.backendastramaco.model.enums.TipoTransporte;

import com.example.backendastramaco.service.TransportistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportistas")
@RequiredArgsConstructor
public class TransportistaController {

    private final TransportistaService service;

    @PostMapping
    public Transportista crear(@RequestBody TransportistaRequestDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<Transportista> listar() {
        return service.listar();
    }

    @GetMapping("/tipo/{tipo}")
    public List<Transportista> listarPorTipo(@PathVariable TipoTransporte tipo) {
        return service.listarPorTipo(tipo);
    }
}