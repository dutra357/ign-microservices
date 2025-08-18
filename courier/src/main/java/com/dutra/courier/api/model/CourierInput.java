package com.dutra.courier.api.model;

import jakarta.validation.constraints.NotBlank;

public class CourierInput {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    public CourierInput() {}

    public CourierInput(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
