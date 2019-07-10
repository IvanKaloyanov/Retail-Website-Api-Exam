package com.koev.retailwebsite.api.services.interfaces;

import com.koev.retailwebsite.api.dto.PurchaseDto;
import com.koev.retailwebsite.api.dto.ReceiptDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * {@link RetailService} Performs retail operations.
 */
public interface RetailService {

    /**
     * BillDto items from the store
     *
     * @param purchases   {@link PurchaseDto} the bill information
     * @param userDetails {@link UserDetails} user details
     * @return {@link ReceiptDto} the receipt of the orderDtop
     */
    ReceiptDto calculateBill(List<PurchaseDto> purchases, UserDetails userDetails);
}
