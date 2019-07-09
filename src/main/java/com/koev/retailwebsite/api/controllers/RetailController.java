package com.koev.retailwebsite.api.controllers;

import com.koev.retailwebsite.api.dto.OrderDtop;
import com.koev.retailwebsite.api.dto.ReceiptDto;
import com.koev.retailwebsite.api.services.interfaces.RetailService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/purchases")
public class RetailController {

    private final RetailService retailService;

    /**
     * Class {@link RetailController} REST API controller performing retail operations
     *
     * @param retailService the {@link RetailService implementation}
     */
    public RetailController(RetailService retailService) {
        this.retailService = retailService;
    }

    /**
     * Endpoint for calculating the billDto
     *
     * @param orderDtop {@link OrderDtop} the orderDtop made
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReceiptDto> calculateBill(@Valid @RequestBody OrderDtop orderDtop,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(retailService.calculateBill(orderDtop, userDetails.getUsername()));

    }
}
