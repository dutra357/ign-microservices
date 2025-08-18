package com.dutra.delivery.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class CourierInput {

    @NotBlank
    private UUID courierid;

    public CourierInput() {

    }

    public CourierInput(UUID courierid) {
        this.courierid = courierid;
    }

    public UUID getCourierid() {
        return courierid;
    }

    public void setCourierid(UUID courierid) {
        this.courierid = courierid;
    }


}
