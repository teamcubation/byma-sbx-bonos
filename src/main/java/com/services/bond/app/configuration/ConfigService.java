package com.services.bond.app.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ConfigService {

    @Value("${spring.application.name:bond}")
    private String applicationName;

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.h2.console.enabled:true}")
    private boolean h2ConsoleEnabled;

    @Value("${app.logging.level:INFO}")
    private String loggingLevel;




}
