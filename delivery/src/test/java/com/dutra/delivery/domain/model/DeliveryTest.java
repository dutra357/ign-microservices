package com.dutra.delivery.domain.model;


import com.dutra.delivery.domain.enuns.DeliveryStatus;
import com.dutra.delivery.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

class DeliveryTest {

    @Test
    void shouldChangeToPlaced() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createValidPreparationDetails());

        delivery.place();

        Assertions.assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        Assertions.assertNotNull(delivery.getPlacedAt());

    }

    @Test
    void shouldNotPlaced() {
        Delivery delivery = Delivery.draft();

        Assertions.assertThrows(DomainException.class, () -> {
            delivery.place();
        });

        Assertions.assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        Assertions.assertNull(delivery.getPlacedAt());
    }

    private Delivery.PreparationDetails createValidPreparationDetails() {

        ContactPoint sender = new ContactPoint("8800000", "Rua São Paulo",
                "100", "sem complemento",
                "João Silva", "(11) 98999-0000");

        ContactPoint recipient = new ContactPoint("7700000", "Rua Miguel Arcanjo da Silva",
                "53", "apto 202",
                "Daniel da Silva Sauro", "(51) 99873-0000");

        return new Delivery.PreparationDetails(sender, recipient,
                new BigDecimal("15.00"),
                new BigDecimal("5.00"), Duration.ofHours(5));
    }

}