package com.dutra.courier.api.controller;

import com.dutra.courier.api.model.CourierInput;
import com.dutra.courier.api.model.CourierPayoutCalculationInput;
import com.dutra.courier.api.model.CourierPayoutResultModel;
import com.dutra.courier.domain.model.Courier;
import com.dutra.courier.domain.repository.CourierRepository;
import com.dutra.courier.domain.service.CourierPayouService;
import com.dutra.courier.domain.service.CourierRegistrationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/couriers")
public class CourierController {

    private final CourierRepository courierRepository;
    private final CourierRegistrationService courierRegistrationService;
    private final CourierPayouService courierPayouService;

    public CourierController(CourierRepository courierRepository,
                             CourierRegistrationService courierRegistrationService, CourierPayouService courierPayouService) {
        this.courierRepository = courierRepository;
        this.courierRegistrationService = courierRegistrationService;
        this.courierPayouService = courierPayouService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Courier create(@Valid @RequestBody CourierInput courierInput) {
        return courierRegistrationService.create(courierInput);
    }

    @PutMapping("/{courierId}")
    @ResponseStatus(HttpStatus.OK)
    public Courier update(@PathVariable UUID courierId,
                          @Valid @RequestBody CourierInput courierInput) {
        return courierRegistrationService.update(courierId, courierInput);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<Courier> findAll(@PageableDefault Pageable pageable) {
        return new PagedModel<>(courierRepository.findAll(pageable));
    }

    @GetMapping("/{courierId}")
    @ResponseStatus(HttpStatus.OK)
    public Courier findById(@PathVariable UUID courierId) {
        return courierRepository.findById(courierId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/payout-calculate")
    public CourierPayoutResultModel calculate(@RequestBody CourierPayoutCalculationInput input) {
        BigDecimal payoutFee = courierPayouService.calculate(input.getDistanceInKm());

        return new CourierPayoutResultModel(payoutFee);
    }


}
