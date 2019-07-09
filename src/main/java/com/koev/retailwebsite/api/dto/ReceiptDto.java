package com.koev.retailwebsite.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Receipt representation
 */
@Data
public class ReceiptDto {

    public ReceiptDto() {
    }

    public ReceiptDto(BillDto order, BigDecimal totalPrice) {
        this.order = new BillDto(order.getPurchaseDto());
        this.totalPrice = totalPrice;
    }

    private BillDto order;
    private BigDecimal totalPrice;
}
