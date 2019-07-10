package com.koev.retailwebsite.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


/**
 * Purchase representation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {

    @NotNull
    private ItemDto item;

    @Positive
    private Integer count;
}
