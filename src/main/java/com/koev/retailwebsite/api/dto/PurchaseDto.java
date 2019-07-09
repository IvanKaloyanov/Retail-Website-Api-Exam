package com.koev.retailwebsite.api.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


/**
 * Purchase representation
 */
@Data
public class PurchaseDto {

    @NotNull
    private ItemDto itemDto;

    @Positive
    private Integer count;
}
