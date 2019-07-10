package com.koev.retailwebsite.api.services;

import com.koev.retailwebsite.api.dto.ItemDto;
import com.koev.retailwebsite.api.dto.PurchaseDto;
import com.koev.retailwebsite.api.dto.ReceiptDto;
import com.koev.retailwebsite.api.entities.User;
import com.koev.retailwebsite.api.services.interfaces.RetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


public class RetailServiceImplTests {

    private static String USERNAME = "Elon Musk";

    private RetailService retailService;
    private UserDetailsService userDetailsService;

    @Test
    @DisplayName("GIVEN an order to calculate a bill, "
            + "WHEN the service is asked to calculate the bill, "
            + "THEN the bill should be calculated correctly.")
    public void test_calculate_bill_success() {

        this.userDetailsService = mock(UserDetailsServiceImpl.class);
        List<String> mockRoles = Arrays.asList("SOME ROLE", "LOYAL");
        User mockUser = new User(1l, USERNAME, null, mockRoles);
        when(userDetailsService.loadUserByUsername(any()))
                .thenReturn(mockUser);

        this.retailService = new RetailServiceImpl(userDetailsService);

        ItemDto item = new ItemDto(
                "Tesla", new BigDecimal
                (100000), ItemDto.ItemType.TECH, "Best car ever");
        PurchaseDto purchase = new PurchaseDto(item, 1);

        List<PurchaseDto> purchases = new ArrayList<PurchaseDto>(Arrays.asList(purchase));

        ReceiptDto receiptDto = retailService.calculateBill(purchases, mockUser);
        // 5% (since the client is loyal) from 100000 is 5000 and
        // other 5000 from the flat discount for the total sum
        assertEquals(new BigDecimal(90000), receiptDto.getTotalPrice()
                , "The bill should be calculated properly");

        assertEquals(purchases.size(), receiptDto.getPurchases().size(),
                "The amount of purchase should be the same.");

        for (int i = 0, n = purchases.size(); i < n; i++) {
            assertEquals(purchases.get(i), receiptDto.getPurchases().get(i),
                    "The items in the bill should be the same as the purchased.");
        }
    }

    @Test
    @DisplayName("GIVEN an order to calculate a bill, "
            + "WHEN the service is asked to calculate the bill," +
            " and user have multiple percentage discounts"
            + "THEN the highest percentage should be applied and" +
            " the bill should be calculated correctly.")
    public void test_calculate_bill_multiple_discount_percentage_success() {

        this.userDetailsService = mock(UserDetailsServiceImpl.class);
        List<String> mockRoles = Arrays.asList("SOME ROLE", "LOYAL", "EMPLOYEE");
        User mockUser = new User(1l, USERNAME, null, mockRoles);
        when(userDetailsService.loadUserByUsername(any()))
                .thenReturn(mockUser);

        this.retailService = new RetailServiceImpl(userDetailsService);

        ItemDto item = new ItemDto(
                "Falcon", new BigDecimal
                (1000000), ItemDto.ItemType.TECH, "Best rocket ever");
        PurchaseDto purchase = new PurchaseDto(item, 1);

        List<PurchaseDto> purchases = new ArrayList<PurchaseDto>(Arrays.asList(purchase));

        ReceiptDto receiptDto = retailService.calculateBill(purchases, mockUser);
        // 30% (since the client is employee) from 1000000 is 300000 and
        // other 50000 from the flat discount for the total sum
        assertEquals(new BigDecimal(650000), receiptDto.getTotalPrice()
                , "The bill should be calculated properly");

        assertEquals(purchases.size(), receiptDto.getPurchases().size(),
                "The amount of purchase should be the same.");

        for (int i = 0, n = purchases.size(); i < n; i++) {
            assertEquals(purchases.get(i), receiptDto.getPurchases().get(i),
                    "The items in the bill should be the same as the purchased.");
        }
    }
}
