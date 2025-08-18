package com.dutra.courier.domain.service;

import com.dutra.courier.api.model.CourierInput;
import com.dutra.courier.domain.model.Courier;
import com.dutra.courier.domain.repository.CourierRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class CourierRegistrationService {

    private final CourierRepository courierRepository;

    public CourierRegistrationService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Transactional
    public Courier create(CourierInput courierInput) {
        Courier newCourier = Courier.brandNew(courierInput.getName(), courierInput.getPhone());

        return courierRepository.save(newCourier);
    }

    @Transactional
    public Courier update(UUID courierId, CourierInput courierInput) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        courier.setPhone(courierInput.getPhone());
        courier.setName(courierInput.getName());

        return courierRepository.save(courier);
    }
}
