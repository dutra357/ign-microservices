package com.dutra.delivery.api.model;

import java.math.BigDecimal;

public class CourierPayoutResultModel {

    private BigDecimal payoutFee;

    public CourierPayoutResultModel(BigDecimal payoutFee) {
        this.payoutFee = payoutFee;
    }

    public BigDecimal getPayoutFee() {
        return payoutFee;
    }

    public void setPayoutFee(BigDecimal payoutFee) {
        this.payoutFee = payoutFee;
    }
}
