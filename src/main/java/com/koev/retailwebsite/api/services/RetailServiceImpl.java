package com.koev.retailwebsite.api.services;

import com.koev.retailwebsite.api.discounts.BasicTotalDiscount;
import com.koev.retailwebsite.api.dto.ItemDto;
import com.koev.retailwebsite.api.dto.OrderDtop;
import com.koev.retailwebsite.api.dto.PurchaseDto;
import com.koev.retailwebsite.api.dto.ReceiptDto;
import com.koev.retailwebsite.api.enums.DiscountRoles;
import com.koev.retailwebsite.api.services.interfaces.RetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * {@link RetailService} implementation
 * for the standard operations
 */
@Service
public class RetailServiceImpl implements RetailService {

    private List<Function> numericDiscounts;
    private Set<ItemDto.ItemType> blacklistedForDiscounts;
    private UserDetailsService userDetailsService;

    @Autowired
    public RetailServiceImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;

        blacklistedForDiscounts = new HashSet<>();
        // Add black listed ItemDto Types
        blacklistedForDiscounts.add(ItemDto.ItemType.GROCERY);

        numericDiscounts = new ArrayList<>();
        // Add all kind of strange discounts for the total billDto
        // based on whatever you want (Date, time, odd, even...)
        numericDiscounts.add(new BasicTotalDiscount(100, 5));
    }

    @Override
    public ReceiptDto calculateBill(OrderDtop orderDtop, String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        BigDecimal total = orderDtop.getBillDto().getPurchaseDto().stream().
                map(i -> i.getItemDto().getPrice().multiply(
                        new BigDecimal(i.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal fixedDiscount = fixedDiscount(total);

        Integer percentageDiscount = getDiscountFromRoles(userDetails.getAuthorities());

        BigDecimal totalPrice = orderDtop.getBillDto().getPurchaseDto().stream().
                map(i -> applyPurchasePercentageDiscount(i, percentageDiscount))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ReceiptDto(orderDtop.getBillDto(), totalPrice.subtract(fixedDiscount));
    }

    /**
     * Apply percentage discount based on the percentage discount card
     *
     * @param purchaseDto {@link PurchaseDto} the purchaseDto to which the discount to be applied
     * @param percentage  {@link Integer} the percentage discount
     * @return {@link BigDecimal} the total sum of the purchaseDto after the applied discount
     */
    private BigDecimal applyPurchasePercentageDiscount(PurchaseDto purchaseDto, Integer percentage) {

        BigDecimal normalPrice = purchaseDto.getItemDto().getPrice().multiply(
                new BigDecimal(purchaseDto.getCount()));
        if (isItemTypeBlacklisted(purchaseDto.getItemDto().getItemType())) {
            return normalPrice;
        }

        return normalPrice.multiply(new BigDecimal(100 - percentage)).divide(new BigDecimal(100));
    }

    /**
     * Apply fixed discounts based on the criteria
     *
     * @param totalPrice {@link BigDecimal} the total price of the billDto
     * @return {@link BigDecimal} to total fixed discount
     */
    private BigDecimal fixedDiscount(BigDecimal totalPrice) {

        BigDecimal currentDiscount = new BigDecimal(0);
        if (numericDiscounts.size() > 0) {
            for (int i = 0, n = numericDiscounts.size(); i < n; i++) {
                currentDiscount = currentDiscount.add((BigDecimal) numericDiscounts.get(i).apply(totalPrice));
            }
        }
        return currentDiscount;
    }

    /**
     * Check if an itemDto type is blacklisted
     *
     * @param itemType the itemDto type
     * @return true if the itemDto is blacklisted, otherwise return false
     */
    private boolean isItemTypeBlacklisted(ItemDto.ItemType itemType) {
        return this.blacklistedForDiscounts.contains(itemType);
    }

    /**
     * @param authorities the user authorities
     * @return {@link Integer} the highest percentage discount based on user's roles
     */
    private Integer getDiscountFromRoles(Collection<? extends GrantedAuthority> authorities) {
        if (authorities.contains(DiscountRoles.EMPLOYEE.name()))
            return DiscountRoles.EMPLOYEE.percentageDiscount;
        if (authorities.contains(DiscountRoles.AFFILIATE.name()))
            return DiscountRoles.AFFILIATE.percentageDiscount;
        if (authorities.contains(DiscountRoles.LOYAL.name()))
            return DiscountRoles.LOYAL.percentageDiscount;
        return 0;
    }
}
