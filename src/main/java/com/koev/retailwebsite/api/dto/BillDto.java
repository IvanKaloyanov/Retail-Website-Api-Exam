package com.koev.retailwebsite.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Bill representation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    @NotNull
    private List<PurchaseDto> purchaseDto;
}
