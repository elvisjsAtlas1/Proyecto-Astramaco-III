package com.example.backendastramaco.service;

import com.example.backendastramaco.dto.TransportistaRequestDTO;
import com.example.backendastramaco.model.Pedido;
import com.example.backendastramaco.model.Transportista;
import com.example.backendastramaco.model.Usuario;
import com.example.backendastramaco.model.enums.EstadoTransportista;
import com.example.backendastramaco.model.enums.TipoTransporte;
import com.example.backendastramaco.repository.TransportistaRepository;
import com.example.backendastramaco.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportistaService {

    private final TransportistaRepository transportistaRepository;
    private final UsuarioRepository usuarioRepository;

    public Transportista crear(TransportistaRequestDTO dto) {

        Transportista t = new Transportista();
        // 🔥 USUARIO (como ya lo haces)
        Usuario usuario = usuarioRepository.findById(dto.usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        t.setUsuario(usuario);

        t.setNombre(dto.nombre);
        t.setApellidos(dto.apellidos);
        t.setDni(dto.dni);
        t.setEdad(dto.edad);
        t.setTipoTransporte(dto.tipoTransporte);
        t.setPlaca(dto.placa);
        t.setVehiculoInfo(dto.vehiculoInfo);
        t.setCapacidad(dto.capacidad);

        // 🔥 AQUÍ LA SOLUCIÓN DEL ESTADO
        if (dto.estado == null || dto.estado.isEmpty()) {
            t.setEstado(EstadoTransportista.ACTIVO);
        } else {
            t.setEstado(EstadoTransportista.valueOf(dto.estado));
        }



        return transportistaRepository.save(t);
    }

    public List<Transportista> listarPorTipo(TipoTransporte tipo) {
        return transportistaRepository
                .findByTipoTransporteAndEstado(tipo, EstadoTransportista.ACTIVO);
    }

    public List<Transportista> listar() {
        return transportistaRepository.findAll();
    }
}