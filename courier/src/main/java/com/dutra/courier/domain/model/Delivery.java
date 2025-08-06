package com.dutra.courier.domain.model;

import com.dutra.courier.domain.enuns.DeliveryStatus;

import java.math.BigDecimal;
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
        this.setStatus(DeliveryStatus.WAITING_FOR_COURIER);
        this.setPlacedAt(OffsetDateTime.now());
    }

    public void pickUp(UUID courierId) {
        this.setCourierId(courierId);
        this.setStatus(DeliveryStatus.IN_TRANSIT);
        this.setAssignedAt(OffsetDateTime.now());
    }

    public void markedAsDelivered() {
        this.setStatus(DeliveryStatus.DELIVERY);
        this.setFulfilledAt(OffsetDateTime.now());
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
}
