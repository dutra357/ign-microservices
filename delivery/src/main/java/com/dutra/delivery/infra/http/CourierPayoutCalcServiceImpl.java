package com.dutra.delivery.infra.http;

import com.dutra.delivery.api.model.CourierPayoutCalculationInput;
import com.dutra.delivery.api.model.CourierPayoutResultModel;
import com.dutra.delivery.domain.service.interfaces.CourierPayoutCalcService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CourierPayoutCalcServiceImpl implements CourierPayoutCalcService {

    private final CourierApiClient courierApiClient;

    public CourierPayoutCalcServiceImpl(CourierApiClient courierApiClient) {
        this.courierApiClient = courierApiClient;
    }

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {

        CourierPayoutResultModel courierPayoutResultModel = courierApiClient
                .payoutCalculation(new CourierPayoutCalculationInput(distanceInKm));

        return courierPayoutResultModel.getPayoutFee();
    }
}
