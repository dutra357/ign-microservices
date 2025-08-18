package com.dutra.delivery.infra;

import com.dutra.delivery.domain.model.ContactPoint;
import com.dutra.delivery.domain.service.DeliveryEstimate;
import com.dutra.delivery.domain.service.interfaces.DeliveryTimeEstimationService;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DeliveryTimeEstimationServiceFakeImpl implements DeliveryTimeEstimationService {

    @Override
    public DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver) {
        return new DeliveryEstimate(Duration.ofHours(3), 3.1);
    }
}
