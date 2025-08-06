package com.dutra.delivery.domain.model;


import com.dutra.delivery.domain.enuns.DeliveryStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeliveryStatusTest {

    @Test
    void draftCanChangeToWaitingForCourier() {
        Assertions.assertTrue(
                DeliveryStatus.DRAFT.canChangeTo(DeliveryStatus.WAITING_FOR_COURIER)
        );
    }

    @Test
    void draftCanChangeToInTransit() {
        Assertions.assertTrue(
                DeliveryStatus.DRAFT.canNotChangeTo(DeliveryStatus.IN_TRANSIT)
        );
    }

}