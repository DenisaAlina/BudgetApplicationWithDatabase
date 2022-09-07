package org.fasttrackit.BudgetApp.model.transaction;

import org.fasttrackit.BudgetApp.exception.ResourceNotFoundException;

public enum Type {

    SELL, BUY;

    public static Type fromStringToEnum(String type) {
        if (Type.BUY.toString().equalsIgnoreCase(type)) {
            return Type.BUY;
        } else if (Type.SELL.toString().equalsIgnoreCase(type)) {
            return Type.SELL;
        } else {
            throw new ResourceNotFoundException("Invalid type!");
        }
    }
}
