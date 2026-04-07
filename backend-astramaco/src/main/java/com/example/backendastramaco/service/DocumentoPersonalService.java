package com.example.backendastramaco.service;

import com.example.backendastramaco.model.DocumentoPersonal;
import com.example.backendastramaco.model.Transportista;
import com.example.backendastramaco.repository.DocumentoPersonalRepository;
import com.example.backendastramaco.repository.TransportistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentoPersonalService {

    private final DocumentoPersonalRepository repository;
    private final TransportistaRepository transportistaRepository;

    public DocumentoPersonal guardar(Long transportistaId, DocumentoPersonal doc) {

        Transportista t = transportistaRepository.findById(transportistaId)
                .orElseThrow(() -> new RuntimeException("Transportista no existe"));

        validarDocumento(doc);

        doc.setTransportista(t);

        return repository.save(doc);
    }

    private void validarDocumento(DocumentoPersonal doc) {

        switch (doc.getTipoDocumento()) {

            case SOAT:
            case REVISION_TECNICA:
                if (doc.getFechaVencimiento() == null) {
                    throw new RuntimeException("Requiere fecha");
                }
                break;

            case LICENCIA:
            case TARJETA_CIRCULACION:
                if (!doc.getValor().equalsIgnoreCase("SI") &&
                        !doc.getValor().equalsIgnoreCase("NO")) {
                    throw new RuntimeException("Debe ser SI o NO");
                }
                break;

            case DNI:
                if (doc.getValor().length() != 8) {
                    throw new RuntimeException("DNI inválido");
                }
                break;
        }
    }
}