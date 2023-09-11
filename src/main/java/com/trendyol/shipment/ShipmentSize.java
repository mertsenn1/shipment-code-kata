package com.trendyol.shipment;

import java.util.*;
import java.util.stream.Collectors;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    private static final List<ShipmentSize> ORDERED_SIZES;

    static {
        ORDERED_SIZES = Arrays.stream(ShipmentSize.values())
                .collect(Collectors.toList());
    }

    public boolean isBiggerThan(ShipmentSize other) {
        return ORDERED_SIZES.indexOf(this) > ORDERED_SIZES.indexOf(other);
    }

    public ShipmentSize getOneUpper() {
        int index = ORDERED_SIZES.indexOf(this);
        if (index < ORDERED_SIZES.size() - 1) {
            return ORDERED_SIZES.get(index + 1);
        } else {
            return ORDERED_SIZES.get(index); // There is no upper size for X_LARGE, return itself
        }
    }
}
