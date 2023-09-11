package com.trendyol.shipment.rules;

import com.trendyol.shipment.ShipmentSize;

import java.util.HashMap;

public class BasketSizeLessThanThresholdRule extends ShipmentSizeRule {
    @Override
    public ShipmentSize applyRule(HashMap<ShipmentSize, Integer> shipmentSizeCounts, int numberOfProductsInBasket) {
        return numberOfProductsInBasket < SHIPMENT_SIZE_THRESHOLD
                ? getLargestShipmentSize(shipmentSizeCounts.keySet()) : null;
    }
}
