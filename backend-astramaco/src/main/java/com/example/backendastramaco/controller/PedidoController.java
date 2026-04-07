package com.example.backendastramaco.controller;

import com.example.backendastramaco.dto.PedidoRequestDTO;
import com.example.backendastramaco.dto.PedidoResponseDTO;
import com.example.backendastramaco.model.Pedido;
import com.example.backendastramaco.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public Pedido crear(@RequestBody PedidoRequestDTO dto) {
        return service.crearPedido(dto);
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }
}