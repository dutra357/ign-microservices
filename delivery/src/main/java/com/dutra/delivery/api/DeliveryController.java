package com.dutra.delivery.api;

import com.dutra.delivery.api.dto.DeliveryInput;
import com.dutra.delivery.domain.model.Delivery;
import com.dutra.delivery.domain.repository.DeliveryRepository;
import com.dutra.delivery.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    private final DeliveryPreparationService service;
    private final DeliveryRepository repository;

    public DeliveryController(DeliveryPreparationService service, DeliveryRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryInput deliveryInput) {
        return service.draft(deliveryInput);
    }

    @PutMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery edit(@PathVariable UUID deliveryId, @RequestBody @Valid DeliveryInput deliveryInput) {
        return service.edit(deliveryId, deliveryInput);
    }

    @GetMapping
    public PagedModel<Delivery> findAll(@PageableDefault Pageable pageable) {
        return new PagedModel<>(repository.findAll(pageable));
    }
}
