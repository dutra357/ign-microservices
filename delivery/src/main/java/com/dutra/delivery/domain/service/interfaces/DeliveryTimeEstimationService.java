package com.dutra.delivery.domain.service.interfaces;

import com.dutra.delivery.domain.model.ContactPoint;
import com.dutra.delivery.domain.service.DeliveryEstimate;

public interface DeliveryTimeEstimationService {

    DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);
}
