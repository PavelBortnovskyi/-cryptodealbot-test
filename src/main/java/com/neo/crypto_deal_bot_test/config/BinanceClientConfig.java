package com.neo.crypto_deal_bot_test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BinanceClientConfig {

    @Value("${binance_fapi_markPrice_url}")
    private String markPriceUrl;

    @Bean(name = "markPriceUrl")
    public String getMarkPriceUrl() {
        return this.markPriceUrl;
    }
}
