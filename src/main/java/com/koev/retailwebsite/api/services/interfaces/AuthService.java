package com.koev.retailwebsite.api.services.interfaces;

import com.koev.retailwebsite.api.dto.AuthDto;
import com.koev.retailwebsite.api.dto.UserDto;

import java.util.Map;

/**
 * {@link AuthService} Performs auth operations.
 */
public interface AuthService {

    /**
     * Obtain an JWT for a given credentials
     *
     * @param authDto {@link AuthDto} the user credentials
     * @return {@link Map<String, String>} map holding the username and signed JWT
     */
    Map<String, String> login(AuthDto authDto);


    /**
     * Obtain an JWT for a given credentials
     *
     * @param userDto {@link UserDto} the user information
     * @return {@link Long} the id of the newly registered user
     */
    Long register(UserDto userDto);
}
