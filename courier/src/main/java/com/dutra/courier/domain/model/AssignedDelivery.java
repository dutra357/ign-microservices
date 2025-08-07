package com.dutra.courier.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class AssignedDelivery {

    @Id
    private UUID id;
    private OffsetDateTime assignedAt;

    @ManyToOne(optional = false)
    private Courier courier;

    protected AssignedDelivery() {
        // Para o Spring
    }

    static AssignedDelivery pending(UUID deliveryId, Courier courier) {
        AssignedDelivery assignedDelivery = new AssignedDelivery();
        assignedDelivery.setId(deliveryId);
        assignedDelivery.setAssignedAt(OffsetDateTime.now());
        assignedDelivery.setCourier(courier);

        return assignedDelivery;
    }

    private Courier getCourier() {
        return courier;
    }

    private void setCourier(Courier courier) {
        this.courier = courier;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setAssignedAt(OffsetDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public UUID getId() {
        return id;
    }

    public OffsetDateTime getAssignedAt() {
        return assignedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AssignedDelivery that = (AssignedDelivery) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AssignedDelivery{" +
                "id=" + id +
                ", assignedAt=" + assignedAt +
                '}';
    }
}
