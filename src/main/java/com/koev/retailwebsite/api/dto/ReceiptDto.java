package com.koev.retailwebsite.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Receipt representation
 */
@Data
@AllArgsConstructor
public class ReceiptDto {
    private List<PurchaseDto> purchases;
    private BigDecimal totalPrice;
}
