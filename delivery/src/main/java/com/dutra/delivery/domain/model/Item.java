package com.dutra.delivery.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Item {

    @Id
    private UUID id;
    private String name;
    private Integer quantity;

    @ManyToOne(optional = false)
    private Delivery delivery;

    static Item brandNew(String name, Integer quantity, Delivery delivery) {
        Item newItem = new Item();
        newItem.setId(UUID.randomUUID());
        newItem.setName(name);
        newItem.setQuantity(quantity);
        newItem.setDelivery(delivery);

        return newItem;
    }

    protected Item() {
        // Solicitado pelo Spring
    }

    private void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    private Delivery getDelivery() {
        return delivery;
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
