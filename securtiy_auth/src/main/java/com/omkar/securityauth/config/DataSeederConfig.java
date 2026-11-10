
package com.omkar.securityauth.config;

import com.omkar.securityauth.service.RoleSeederService;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataSeederConfig {

    private final RoleSeederService roleSeederService;

    @Bean
    public ApplicationRunner dataSeeder() {

        return args -> roleSeederService.seed();
    }
}
