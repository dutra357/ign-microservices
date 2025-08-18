package com.dutra.delivery.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class ContactPointInput {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    // Opcional
    private String complement;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    public ContactPointInput() {

    }

    public ContactPointInput(String zipCode, String street,
                             String number, String complement,
                             String name, String phone) {
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.name = name;
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContactPointInput that = (ContactPointInput) o;
        return Objects.equals(zipCode, that.zipCode) && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(complement, that.complement) && Objects.equals(name, that.name) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, street, number, complement, name, phone);
    }

    @Override
    public String toString() {
        return "ContactPointInput{" +
                "zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
