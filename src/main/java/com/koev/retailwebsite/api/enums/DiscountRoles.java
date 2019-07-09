package com.koev.retailwebsite.api.enums;

/**
 * {@link DiscountRoles} holding the amount of discount for a set of roles
 */
public enum DiscountRoles {

    LOYAL(5),
    AFFILIATE(20),
    EMPLOYEE(30);

    final public Integer percentageDiscount;

    DiscountRoles(Integer percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }
}
