package com.koev.retailwebsite.api.condig;

import com.koev.retailwebsite.api.RetailWebsiteApiApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackageClasses = RetailWebsiteApiApplication.class)
public class EncryptionConfig {

    /**
     * Bean for injecting PasswordEncoder instances
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
