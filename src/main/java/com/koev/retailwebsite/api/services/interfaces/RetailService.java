package com.koev.retailwebsite.api.services.interfaces;

import com.koev.retailwebsite.api.dto.OrderDtop;
import com.koev.retailwebsite.api.dto.ReceiptDto;

/**
 * {@link RetailService} Performs retail operations.
 */
public interface RetailService {

    /**
     * BillDto items from the store
     *
     * @param orderDtop    {@link OrderDtop} the orderDtop information
     * @param username {@link String} username
     * @return {@link ReceiptDto} the receipt of the orderDtop
     */
    ReceiptDto calculateBill(OrderDtop orderDtop, String username);
}
