package com.trendyol.shipment.rules;

import com.trendyol.shipment.ShipmentSize;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class ShipmentSizeRule {
    protected final static int SHIPMENT_SIZE_THRESHOLD = 3;

    public abstract ShipmentSize applyRule(HashMap<ShipmentSize, Integer> shipmentSizeCounts, int numberOfProductsInBasket);

    protected final ShipmentSize getLargestShipmentSize(Set<ShipmentSize> shipmentSizeSet) {
        ShipmentSize largestShipmentSize = null;
        for (ShipmentSize shipmentSize : shipmentSizeSet) {
            if (shipmentSize.isBiggerThan(largestShipmentSize))
                largestShipmentSize = shipmentSize;
        }
        return largestShipmentSize;
    }

    protected final ShipmentSize getShipmentSizeWithThresholdOccurrence(HashMap<ShipmentSize, Integer> shipmentSizeCounts) {
        for (Map.Entry<ShipmentSize, Integer> entry : shipmentSizeCounts.entrySet()) {
            ShipmentSize shipmentSize = entry.getKey();
            int count = entry.getValue();

            if (count >= SHIPMENT_SIZE_THRESHOLD) {
                return shipmentSize;
            }
        }
        return null;
    }
}
