package com.dutra.delivery.api;

import com.dutra.delivery.api.dto.CourierInput;
import com.dutra.delivery.api.dto.DeliveryInput;
import com.dutra.delivery.domain.model.Delivery;
import com.dutra.delivery.domain.repository.DeliveryRepository;
import com.dutra.delivery.domain.service.DeliveryCheckpointService;
import com.dutra.delivery.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    private final DeliveryPreparationService service;
    private final DeliveryRepository repository;
    private final DeliveryCheckpointService deliveryCheckpointService;

    public DeliveryController(DeliveryPreparationService service, DeliveryRepository repository, DeliveryCheckpointService deliveryCheckpointService) {
        this.service = service;
        this.repository = repository;
        this.deliveryCheckpointService = deliveryCheckpointService;
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

    @GetMapping("/{deliveryId}")
    public Delivery findById(@PathVariable UUID deliveryId) {
        return repository.findById(deliveryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Um endpoint deve ser um substantivo / Padrão REST
    @PostMapping("/{deliveryId}/placement")
    public void place(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.place(deliveryId);
    }

    @PostMapping("/{deliveryId}/pickups")
    public void pickUp(@PathVariable UUID deliveryId, @Valid @RequestBody CourierInput courierInput) {
        deliveryCheckpointService.pickUp(deliveryId, courierInput.getCourierid());

    }

    @PostMapping("/{deliveryId}/completion")
    public void complete(@PathVariable UUID deliveryId) {
        deliveryCheckpointService.complete(deliveryId);

    }
}
