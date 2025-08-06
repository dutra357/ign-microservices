package com.dutra.delivery.domain.model;

import com.dutra.delivery.domain.enuns.DeliveryStatus;
import com.dutra.delivery.domain.exception.DomainException;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;

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

    public static Delivery draft() {
        Delivery delivery = new Delivery();

        delivery.setId(UUID.randomUUID());
        delivery.setStatus(DeliveryStatus.DRAFT);
        delivery.setTotalItems(0);
        delivery.setTotalCost(BigDecimal.ZERO);
        delivery.setCourierPayout(BigDecimal.ZERO);
        delivery.setDistanceFee(BigDecimal.ZERO);

        return delivery;
    }

    protected Delivery() {
        // Exigido pelo Spring
    }

    public UUID addItem(String name, int quantity) {
        Item item = Item.brandNew(name, quantity);
        items.add(item);
        calculateTotalItems();
        return  item.getId();
    }

    public void removeitem(UUID itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
        calculateTotalItems();
    }

    public void removeItems() {
        items.clear();
        calculateTotalItems();
    }

    public void editPreparationDetails(PreparationDetails details) {
        verifyIfCanBeEdited();
        
        setSender(details.getSender());
        setRecipient(details.getRecipient());
        setDistanceFee(details.getDistanceFee());
        setCourierPayout(details.getCourierPayout());

        setExpectedDeliveryAt(OffsetDateTime.now().plus(details.getExpectedDeliveryTime()));
        setTotalCost(this.getDistanceFee().add(this.getCourierPayout()));
    }

    private void verifyIfCanBeEdited() {
        if (!getStatus().equals(DeliveryStatus.DRAFT)) {
            throw new DomainException("Status is DRAFT.");
        }
    }

    public void changeItemQuantity(UUID itemId, int itemQuantity) {
        Item item = getItems().stream().filter(i -> i.getId().equals(itemId))
                .findFirst().orElseThrow();

        item.setQuantity(itemQuantity);
        calculateTotalItems();
    }

    private void calculateTotalItems() {
        int total = items.stream().mapToInt(Item::getQuantity).sum();
        setTotalItems(total);
    }

    public void place() {
        verifyIfCanBePlaced();
        this.changeStatusTo(DeliveryStatus.WAITING_FOR_COURIER);
        this.setPlacedAt(OffsetDateTime.now());
    }

    private void verifyIfCanBePlaced() {
        if (!isFilled()) {
            throw new DomainException("Not can be placed.");
        }

        if (!getStatus().equals(DeliveryStatus.DRAFT)) {
            throw new DomainException("Status error.");
        }
    }

    private boolean isFilled() {
        return this.getSender() != null
                && this.getRecipient() != null
                && this.getTotalCost() != null;
    }

    public void pickUp(UUID courierId) {
        this.setCourierId(courierId);
        this.changeStatusTo(DeliveryStatus.IN_TRANSIT);
        this.setAssignedAt(OffsetDateTime.now());
    }

    public void markedAsDelivered() {
        this.changeStatusTo(DeliveryStatus.DELIVERY);
        this.setFulfilledAt(OffsetDateTime.now());
    }

    private void changeStatusTo(DeliveryStatus newStatus) {

        if (newStatus != null && this.getStatus().canNotChangeTo(newStatus)) {
            throw new DomainException("Invalid status transition.");
        }

        this.setStatus(newStatus);
    }

    public UUID getId() {
        return id;
    }

    public UUID getCourierId() {
        return courierId;
    }

    public OffsetDateTime getPlacedAt() {
        return placedAt;
    }

    public OffsetDateTime getAssignedAt() {
        return assignedAt;
    }

    public OffsetDateTime getExpectedDeliveryAt() {
        return expectedDeliveryAt;
    }

    public OffsetDateTime getFulfilledAt() {
        return fulfilledAt;
    }

    public BigDecimal getDistanceFee() {
        return distanceFee;
    }

    public BigDecimal getCourierPayout() {
        return courierPayout;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public ContactPoint getSender() {
        return sender;
    }

    public ContactPoint getRecipient() {
        return recipient;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    private void setRecipient(ContactPoint recipient) {
        this.recipient = recipient;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setCourierId(UUID courierId) {
        this.courierId = courierId;
    }

    private void setPlacedAt(OffsetDateTime placedAt) {
        this.placedAt = placedAt;
    }

    private void setAssignedAt(OffsetDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    private void setExpectedDeliveryAt(OffsetDateTime expectedDeliveryAt) {
        this.expectedDeliveryAt = expectedDeliveryAt;
    }

    private void setFulfilledAt(OffsetDateTime fulfilledAt) {
        this.fulfilledAt = fulfilledAt;
    }

    private void setDistanceFee(BigDecimal distanceFee) {
        this.distanceFee = distanceFee;
    }

    private void setCourierPayout(BigDecimal courierPayout) {
        this.courierPayout = courierPayout;
    }

    private void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    private void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    private void setSender(ContactPoint sender) {
        this.sender = sender;
    }

    private void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
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

    public static class PreparationDetails {
        private ContactPoint sender;
        private ContactPoint recipient;
        private BigDecimal distanceFee;
        private BigDecimal courierPayout;
        private Duration expectedDeliveryTime;

        public PreparationDetails(ContactPoint sender,
                                  ContactPoint recipient, BigDecimal distanceFee,
                                  BigDecimal courierPayout, Duration expectedDeliveryTime) {
            this.sender = sender;
            this.recipient = recipient;
            this.distanceFee = distanceFee;
            this.courierPayout = courierPayout;
            this.expectedDeliveryTime = expectedDeliveryTime;
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

        public Duration getExpectedDeliveryTime() {
            return expectedDeliveryTime;
        }

        public void setExpectedDeliveryTime(Duration expectedDeliveryTime) {
            this.expectedDeliveryTime = expectedDeliveryTime;
        }

        @Override
        public String toString() {
            return "PreparationDetails{" +
                    "sender=" + sender +
                    ", recipient=" + recipient +
                    ", distanceFee=" + distanceFee +
                    ", courierPayout=" + courierPayout +
                    ", expectedDeliveryTime=" + expectedDeliveryTime +
                    '}';
        }
    }
}
