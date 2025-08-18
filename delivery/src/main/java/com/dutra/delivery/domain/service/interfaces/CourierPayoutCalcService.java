package com.dutra.delivery.domain.service.interfaces;

import java.math.BigDecimal;

public interface CourierPayoutCalcService {

    BigDecimal calculatePayout(Double distanceInKm);


}
