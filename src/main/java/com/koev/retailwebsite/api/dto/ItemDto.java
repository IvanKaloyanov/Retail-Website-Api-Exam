package com.koev.retailwebsite.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


/**
 * Item representation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    public enum ItemType {
        GROCERY,
        CLOTHES,
        OTHER,
    }

    @NotNull
    private String name;

    @Positive
    private BigDecimal price;

    @NotNull
    private ItemType itemType;

    private String description;
}