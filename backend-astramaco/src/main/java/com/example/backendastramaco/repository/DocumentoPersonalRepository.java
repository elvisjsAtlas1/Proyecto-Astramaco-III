package com.example.backendastramaco.repository;

import com.example.backendastramaco.model.DocumentoPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoPersonalRepository extends JpaRepository<DocumentoPersonal, Long> {}