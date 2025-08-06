package com.dutra.delivery.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Item {

    private UUID id;
    private String name;
    private Integer quantity;

    static Item brandNew(String name, Integer quantity) {
        Item newItem = new Item();
        newItem.setId(UUID.randomUUID());
        newItem.setName(name);
        newItem.setQuantity(quantity);

        return newItem;
    }

    protected Item() {
        // Solicitado pelo Spring
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    protected void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
