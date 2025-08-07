package com.dutra.courier.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.OffsetDateTime;
import java.util.*;

@Entity
public class Courier {

    @Id
    private UUID id;
    private String name;
    private String phone;

    private Integer fulfilledDeliveriesQuantity;
    private Integer pendingDeliveriesQuantity;
    private OffsetDateTime lastFulfilledDeliveryAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "courier")
    private final List<AssignedDelivery> pendingDeliveries = new ArrayList<>();

    public static Courier brandNew(String name, String phone) {
        Courier courier = new Courier();

        courier.setId(UUID.randomUUID());
        courier.setName(name);
        courier.setPhone(phone);

        courier.setFulfilledDeliveriesQuantity(0);
        courier.setPendingDeliveriesQuantity(0);

        return courier;
    }

    protected Courier() {
        // Controle para Spring
    }

    public void assign(UUID deliveryId) {
        this.pendingDeliveries.add(AssignedDelivery.pending(deliveryId, this));

        this.pendingDeliveriesQuantity++;
    }

    public void fulfill(UUID deliveryId) {
        AssignedDelivery delivery = this.pendingDeliveries.stream().filter(d -> d.getId().equals(deliveryId))
                .findFirst().orElseThrow();

        this.pendingDeliveries.remove(delivery);
        this.pendingDeliveriesQuantity--;
        this.fulfilledDeliveriesQuantity++;
        this.lastFulfilledDeliveryAt = OffsetDateTime.now();

    }

    private void setFulfilledDeliveriesQuantity(Integer fulfilledDeliveriesQuantity) {
        this.fulfilledDeliveriesQuantity = fulfilledDeliveriesQuantity;
    }

    private void setPendingDeliveriesQuantity(Integer pendingDeliveriesQuantity) {
        this.pendingDeliveriesQuantity = pendingDeliveriesQuantity;
    }

    private void setLastFulfilledDeliveryAt(OffsetDateTime lastFulfilledDeliveryAt) {
        this.lastFulfilledDeliveryAt = lastFulfilledDeliveryAt;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getFulfilledDeliveriesQuantity() {
        return fulfilledDeliveriesQuantity;
    }

    public Integer getPendingDeliveriesQuantity() {
        return pendingDeliveriesQuantity;
    }

    public OffsetDateTime getLastFulfilledDeliveryAt() {
        return lastFulfilledDeliveryAt;
    }

    public List<AssignedDelivery> getPendingDeliveries() {
        return Collections.unmodifiableList(pendingDeliveries);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Objects.equals(id, courier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
