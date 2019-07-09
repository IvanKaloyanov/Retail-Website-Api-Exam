package com.koev.retailwebsite.api.discounts;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * {@link BasicTotalDiscount} implementation of X discount for every Y amount in the total billDto
 */
public class BasicTotalDiscount implements Function<BigDecimal, BigDecimal> {

    public BasicTotalDiscount(BigDecimal pricePoint, BigDecimal discountSize) {
        this.pricePoint = pricePoint;
        this.discountSize = discountSize;

    }

    public BasicTotalDiscount(Integer pricePoint, Integer discountSize) {
        this.pricePoint = new BigDecimal(pricePoint);
        this.discountSize = new BigDecimal(discountSize);
    }

    private BigDecimal pricePoint;
    private BigDecimal discountSize;

    @Override
    public BigDecimal apply(BigDecimal totalPrice) {
        return discountSize.multiply(new BigDecimal(totalPrice.divide(this.pricePoint).intValue()));
    }
}
