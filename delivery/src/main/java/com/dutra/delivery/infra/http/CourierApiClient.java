package com.dutra.delivery.infra.http;

import com.dutra.delivery.api.model.CourierPayoutCalculationInput;
import com.dutra.delivery.api.model.CourierPayoutResultModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/v1/couriers")
public interface CourierApiClient {

    @PostExchange("/payout-calculate")
    CourierPayoutResultModel payoutCalculation(@RequestBody CourierPayoutCalculationInput input);

}