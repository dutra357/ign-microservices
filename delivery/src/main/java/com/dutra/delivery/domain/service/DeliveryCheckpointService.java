package com.dutra.delivery.domain.service;

import com.dutra.delivery.domain.exception.DomainException;
import com.dutra.delivery.domain.model.Delivery;
import com.dutra.delivery.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeliveryCheckpointService {

    private final DeliveryRepository repository;

    public DeliveryCheckpointService(DeliveryRepository repository) {
        this.repository = repository;
    }

    public void place(UUID deliveryId) {
        Delivery delivery = repository.findById(deliveryId)
                .orElseThrow(() -> new DomainException("ID não encontrado."));

        delivery.place();
        repository.save(delivery);
    }

    public void pickUp(UUID deliveryId, UUID courierId) {
        Delivery delivery = repository.findById(deliveryId)
                .orElseThrow(() -> new DomainException("ID não encontrado."));

        delivery.pickUp(courierId);
        repository.save(delivery);
    }

    public void complete(UUID deliveryId) {
        Delivery delivery = repository.findById(deliveryId)
                .orElseThrow(() -> new DomainException("ID não encontrado."));

        delivery.markedAsDelivered();
        repository.save(delivery);
    }
}
