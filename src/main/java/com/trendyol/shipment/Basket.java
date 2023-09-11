package com.trendyol.shipment;

import com.trendyol.shipment.rules.*;

import java.util.HashMap;
import java.util.List;

public class Basket {
    private final static List<ShipmentSizeRule> SHIPMENT_SIZE_CALCULATION_RULES = List.of(new BasketSizeLessThanThresholdRule(),
                                                                                          new MoreThanThresholdMatchingRule(),
                                                                                          new MoreThanThresholdNoMatchRule()
                                                                                          );

    private List<Product> products;

    public ShipmentSize getShipmentSize() {

        if (products.isEmpty()) {
            throw new IllegalStateException("The basket cannot be empty.");
        }
        HashMap<ShipmentSize, Integer> shipmentSizeCounts = getShipmentSizeCounts();
        for (ShipmentSizeRule shipmentSizeRule : SHIPMENT_SIZE_CALCULATION_RULES) {
            ShipmentSize shipmentSize = shipmentSizeRule.applyRule(shipmentSizeCounts, products.size());
            if (shipmentSize != null)
                return shipmentSize;
        }
        throw new IllegalStateException("Shipment Size could not be calculated.");
    }

    private HashMap<ShipmentSize, Integer> getShipmentSizeCounts() {
        HashMap<ShipmentSize, Integer> sizeCounts = new HashMap<>();
        for (Product product : products) {
            ShipmentSize productSize = product.getSize();
            sizeCounts.putIfAbsent(productSize, 0);
            sizeCounts.put(productSize, sizeCounts.get(productSize) + 1);
        }
        return sizeCounts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
