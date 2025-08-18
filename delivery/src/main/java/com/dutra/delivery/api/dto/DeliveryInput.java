package com.dutra.delivery.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeliveryInput {

    @NotNull
    @Valid
    private ContactPointInput sender;

    @NotNull
    @Valid
    private ContactPointInput receiver;

    @NotEmpty
    @Valid
    private final List<ItemInput> items = new ArrayList<>();


    public DeliveryInput() {

    }

    public DeliveryInput(ContactPointInput sender,
                         ContactPointInput receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public ContactPointInput getSender() {
        return sender;
    }

    public void setSender(ContactPointInput sender) {
        this.sender = sender;
    }

    public ContactPointInput getReceiver() {
        return receiver;
    }

    public void setReceiver(ContactPointInput receiver) {
        this.receiver = receiver;
    }

    public List<ItemInput> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryInput that = (DeliveryInput) o;
        return Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, items);
    }

    @Override
    public String toString() {
        return "DeliveryInput{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", items=" + items +
                '}';
    }
}
