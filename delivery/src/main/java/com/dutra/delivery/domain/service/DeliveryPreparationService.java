package com.dutra.delivery.domain.service;

import com.dutra.delivery.api.dto.ContactPointInput;
import com.dutra.delivery.api.dto.DeliveryInput;
import com.dutra.delivery.api.dto.ItemInput;
import com.dutra.delivery.domain.exception.DomainException;
import com.dutra.delivery.domain.model.ContactPoint;
import com.dutra.delivery.domain.model.Delivery;
import com.dutra.delivery.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@Service
public class DeliveryPreparationService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryPreparationService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public Delivery draft(DeliveryInput deliveryInput) {
        Delivery delivery = Delivery.draft();

        handlePreparation(deliveryInput, delivery);
        
        return deliveryRepository.save(delivery);
    }

    @Transactional
    public Delivery edit(UUID deliveryId, DeliveryInput deliveryInput) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
                () -> new DomainException("DeliveryId não encontrado.")
        );

        delivery.removeItems();
        handlePreparation(deliveryInput, delivery);
        return  deliveryRepository.save(delivery);
    }

    private void handlePreparation(DeliveryInput deliveryInput, Delivery delivery) {
        ContactPointInput senderInput = deliveryInput.getSender();
        ContactPointInput recipientInput = deliveryInput.getReceiver();

        ContactPoint sender = new ContactPoint(senderInput.getZipCode(), senderInput.getStreet(),
                senderInput.getNumber(), senderInput.getComplement(),
                senderInput.getName(), senderInput.getPhone());

        ContactPoint recipient = new ContactPoint(recipientInput.getZipCode(), recipientInput.getStreet(),
                recipientInput.getNumber(), recipientInput.getComplement(),
                recipientInput.getName(), recipientInput.getPhone());


        Duration expectedTime = Duration.ofHours(3);
        BigDecimal payout = new BigDecimal("10");

        var preparationDetails = new Delivery.PreparationDetails(sender, recipient, new BigDecimal("10"), payout, expectedTime);

        delivery.editPreparationDetails(preparationDetails);

        for (ItemInput item : deliveryInput.getItems()) {
            delivery.addItem(item.getName(), item.getQuantity());
        }
    }
}
