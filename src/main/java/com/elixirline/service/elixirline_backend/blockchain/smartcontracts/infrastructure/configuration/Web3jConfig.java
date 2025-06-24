package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.infrastructure.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import java.util.concurrent.TimeUnit;

@Configuration
public class Web3jConfig {
    private static final Logger log = LoggerFactory.getLogger(Web3jConfig.class);

    @Value("${web3.network.url}")
    private String networkUrl;

    @Value("${web3.private.key}")
    private String privateKey;

    @Bean
    public Web3j web3j() {
        log.info("Configurando Web3j con URL: {}", networkUrl);
        return Web3j.build(
                new HttpService(
                        networkUrl,
                        HttpService.getOkHttpClientBuilder()
                                .connectTimeout(5, TimeUnit.MINUTES)
                                .readTimeout(5, TimeUnit.MINUTES)
                                .writeTimeout(5, TimeUnit.MINUTES)
                                .build()
                )
        );
    }

    @Bean
    public Credentials credentials() {
        String cleanPrivateKey = privateKey.startsWith("0x") ?
                privateKey.substring(2) : privateKey;
        return Credentials.create(cleanPrivateKey);
    }
}