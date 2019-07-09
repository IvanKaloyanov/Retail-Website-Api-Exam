package com.koev.retailwebsite.api.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Auth request representation
 */
@Data
@Builder
public class AuthDto {

    @NotNull
    @Size(min = 6, max = 48)
    private String username;

    @NotNull
    @Size(min = 6, max = 48)
    private String password;
}
