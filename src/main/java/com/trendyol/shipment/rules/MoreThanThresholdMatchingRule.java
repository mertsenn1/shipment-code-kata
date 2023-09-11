package com.trendyol.shipment.rules;

import com.trendyol.shipment.ShipmentSize;

import java.util.HashMap;

public class MoreThanThresholdMatchingRule extends ShipmentSizeRule {

    @Override
    public ShipmentSize applyRule(HashMap<ShipmentSize, Integer> shipmentSizeCounts, int numberOfProductsInBasket) {
        if (numberOfProductsInBasket < SHIPMENT_SIZE_THRESHOLD) {
            return null;
        }

        ShipmentSize shipmentSizeWithThresholdCount = getShipmentSizeWithThresholdOccurrence(shipmentSizeCounts);
        return shipmentSizeWithThresholdCount == null ? null : shipmentSizeWithThresholdCount.getOneUpper();
    }
}
