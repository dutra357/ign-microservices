package com.dutra.delivery.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class ItemInput {

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private int quantity;

    public ItemInput() {

    }

    public ItemInput(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemInput itemInput = (ItemInput) o;
        return quantity == itemInput.quantity && Objects.equals(name, itemInput.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }

    @Override
    public String toString() {
        return "ItemInput{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
