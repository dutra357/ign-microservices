package com.dutra.courier.domain.model;

import com.dutra.courier.domain.enuns.DeliveryStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Delivery {

    private UUID id;
    private UUID courierId;

    private OffsetDateTime placedAt;
    private OffsetDateTime assignedAt;
    private OffsetDateTime expectedDeliveryAt;
    private OffsetDateTime fulfilledAt;

    private BigDecimal distanceFee;
    private BigDecimal courierPayout;
    private BigDecimal totalCost;

    private DeliveryStatus status;

    private ContactPoint sender;
    private ContactPoint recipient;

    private Integer totalItems;

    private final List<Item> items = new ArrayList<>();

    public Delivery() {

    }

    public Delivery(UUID id, UUID courierId, OffsetDateTime placedAt,
                    OffsetDateTime assignedAt, OffsetDateTime expectedDeliveryAt,
                    OffsetDateTime fulfilledAt, BigDecimal distanceFee, BigDecimal courierPayout,
                    BigDecimal totalCost, DeliveryStatus status, ContactPoint sender,
                    ContactPoint recipient, Integer totalItems) {
        this.id = id;
        this.courierId = courierId;
        this.placedAt = placedAt;
        this.assignedAt = assignedAt;
        this.expectedDeliveryAt = expectedDeliveryAt;
        this.fulfilledAt = fulfilledAt;
        this.distanceFee = distanceFee;
        this.courierPayout = courierPayout;
        this.totalCost = totalCost;
        this.status = status;
        this.sender = sender;
        this.recipient = recipient;
        this.totalItems = totalItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCourierId() {
        return courierId;
    }

    public void setCourierId(UUID courierId) {
        this.courierId = courierId;
    }

    public OffsetDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(OffsetDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public OffsetDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(OffsetDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public OffsetDateTime getExpectedDeliveryAt() {
        return expectedDeliveryAt;
    }

    public void setExpectedDeliveryAt(OffsetDateTime expectedDeliveryAt) {
        this.expectedDeliveryAt = expectedDeliveryAt;
    }

    public OffsetDateTime getFulfilledAt() {
        return fulfilledAt;
    }

    public void setFulfilledAt(OffsetDateTime fulfilledAt) {
        this.fulfilledAt = fulfilledAt;
    }

    public BigDecimal getDistanceFee() {
        return distanceFee;
    }

    public void setDistanceFee(BigDecimal distanceFee) {
        this.distanceFee = distanceFee;
    }

    public BigDecimal getCourierPayout() {
        return courierPayout;
    }

    public void setCourierPayout(BigDecimal courierPayout) {
        this.courierPayout = courierPayout;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public ContactPoint getSender() {
        return sender;
    }

    public void setSender(ContactPoint sender) {
        this.sender = sender;
    }

    public ContactPoint getRecipient() {
        return recipient;
    }

    public void setRecipient(ContactPoint recipient) {
        this.recipient = recipient;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(id, delivery.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", courierId=" + courierId +
                ", placedAt=" + placedAt +
                ", assignedAt=" + assignedAt +
                ", expectedDeliveryAt=" + expectedDeliveryAt +
                ", fulfilledAt=" + fulfilledAt +
                ", distanceFee=" + distanceFee +
                ", courierPayout=" + courierPayout +
                ", totalCost=" + totalCost +
                ", status=" + status +
                ", totalItems=" + totalItems +
                ", items=" + items +
                '}';
    }
}
