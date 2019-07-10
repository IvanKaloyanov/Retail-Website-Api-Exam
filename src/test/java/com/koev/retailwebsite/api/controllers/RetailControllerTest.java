package com.koev.retailwebsite.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koev.retailwebsite.api.RetailWebsiteApiApplication;
import com.koev.retailwebsite.api.dto.ItemDto;
import com.koev.retailwebsite.api.dto.PurchaseDto;
import com.koev.retailwebsite.api.security.JwtTokenProvider;
import com.koev.retailwebsite.api.services.interfaces.RetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = RetailWebsiteApiApplication.class)
@WebMvcTest(RetailController.class)
public class RetailControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private RetailService retailService;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    private static final String API_PREFIX = "/api/v1/retail";

    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("GIVЕN а proper request to calculate the bill, "
            + "WHEN a proper request body is passed, "
            + "THEN then we should expect status 200 OKAY.")
    public void test_calculate_bull_success() throws Exception {
        given(retailService.calculateBill(any(), any())).willReturn(null);
        ItemDto item = new ItemDto(
                "Tesla", new BigDecimal
                (100000), ItemDto.ItemType.TECH, "Best car ever");
        List<PurchaseDto> purchases = Arrays.asList(new PurchaseDto(item, 1));

        String payload = objectMapper.writeValueAsString(purchases);

        MvcResult result = mockMvc.perform(
                post(String.format("%s", API_PREFIX) + "/discounts").contentType("application/json")
                        .content(payload))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("GIVЕN а wrong request to calculate the bill, "
            + "WHEN a wrong request body is passed, "
            + "THEN we should expect status 400 BAD REQUEST.")
    public void test_get_all_raw_events_succdess() throws Exception {
        given(retailService.calculateBill(any(), any())).willReturn(null);

        String payload = "some wrong payload";

        MvcResult result = mockMvc.perform(
                post(String.format("%s", API_PREFIX + "/discounts")).contentType("application/json")
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
