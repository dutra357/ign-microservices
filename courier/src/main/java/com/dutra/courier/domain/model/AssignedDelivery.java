package com.dutra.courier.domain.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

public class AssignedDelivery {

    private UUID id;
    private OffsetDateTime assignedAt;

    protected AssignedDelivery() {
        // Para o Spring
    }

    static AssignedDelivery pending(UUID deliveryId) {
        AssignedDelivery assignedDelivery = new AssignedDelivery();
        assignedDelivery.setId(deliveryId);
        assignedDelivery.setAssignedAt(OffsetDateTime.now());

        return assignedDelivery;
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
