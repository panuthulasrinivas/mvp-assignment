package com.srinu.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.srinu.entity")
@EnableJpaRepositories("com.srinu.repository")
public class JpaConfig {
}
