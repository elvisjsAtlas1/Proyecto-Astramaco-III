package com.example.backendastramaco.repository;

import com.example.backendastramaco.model.Carga;
import com.example.backendastramaco.model.enums.TipoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CargaRepository extends JpaRepository<Carga, Long> {
    Optional<Carga> findByTransportistaIdAndTipoMaterial(Long id, TipoMaterial material);
}