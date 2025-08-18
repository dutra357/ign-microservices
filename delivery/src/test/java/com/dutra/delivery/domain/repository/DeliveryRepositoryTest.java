package com.dutra.delivery.domain.repository;

import com.dutra.delivery.domain.model.ContactPoint;
import com.dutra.delivery.domain.model.Delivery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository repository;

    @Test
    void shouldPersist() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createValidPreparationDetails());

        delivery.addItem("Computador", 2);
        delivery.addItem("Notebook", 2);

        repository.save(delivery);

        Delivery persistDelivery = repository.findById(delivery.getId()).orElseThrow();
        Assertions.assertEquals(2, persistDelivery.getItems().size());
    }

    private Delivery.PreparationDetails createValidPreparationDetails() {

        ContactPoint sender = new ContactPoint("8800000", "Rua São Paulo",
                "100", "sem complemento",
                "João Silva", "(11) 98999-0000");

        ContactPoint recipient = new ContactPoint("7700000", "Rua Miguel Arcanjo da Silva",
                "53", "apto 202",
                "Daniel da Silva Sauro", "(51) 99873-0000");

        return new Delivery.PreparationDetails(sender, recipient,
                new BigDecimal("15.00"),
                new BigDecimal("5.00"), Duration.ofHours(5));
    }

}