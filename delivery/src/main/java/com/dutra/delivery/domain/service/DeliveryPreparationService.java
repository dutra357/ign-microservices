package com.dutra.delivery.domain.service;

import com.dutra.delivery.api.dto.ContactPointInput;
import com.dutra.delivery.api.dto.DeliveryInput;
import com.dutra.delivery.api.dto.ItemInput;
import com.dutra.delivery.domain.exception.DomainException;
import com.dutra.delivery.domain.model.ContactPoint;
import com.dutra.delivery.domain.model.Delivery;
import com.dutra.delivery.domain.repository.DeliveryRepository;
import com.dutra.delivery.domain.service.interfaces.CourierPayoutCalcService;
import com.dutra.delivery.domain.service.interfaces.DeliveryTimeEstimationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.UUID;

@Service
public class DeliveryPreparationService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryTimeEstimationService deliveryTimeEstimationService;
    private final CourierPayoutCalcService courierPayoutCalcService;

    public DeliveryPreparationService(DeliveryRepository deliveryRepository,
                                      DeliveryTimeEstimationService deliveryTimeEstimationService,
                                      CourierPayoutCalcService courierPayoutCalcService) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryTimeEstimationService = deliveryTimeEstimationService;
        this.courierPayoutCalcService = courierPayoutCalcService;
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

        DeliveryEstimate estimate = deliveryTimeEstimationService.estimate(sender, recipient);
        BigDecimal payout = courierPayoutCalcService.calculatePayout(estimate.getDistanceInKm());

        BigDecimal distanceFee = calculateFee(estimate.getDistanceInKm());

        var preparationDetails = new Delivery.PreparationDetails(sender, recipient, distanceFee, payout, estimate.getEstimatedTime());

        delivery.editPreparationDetails(preparationDetails);

        for (ItemInput item : deliveryInput.getItems()) {
            delivery.addItem(item.getName(), item.getQuantity());
        }
    }

    private BigDecimal calculateFee(Double distanceInKm) {
        return new BigDecimal("3")
                .multiply(BigDecimal.valueOf(distanceInKm))
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
