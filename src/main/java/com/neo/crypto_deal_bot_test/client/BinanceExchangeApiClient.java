package com.neo.crypto_deal_bot_test.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.crypto_deal_bot_test.domain.TradingPair;
import lombok.RequiredArgsConstructor;;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@RequiredArgsConstructor
public class BinanceExchangeApiClient implements ExchangeApiClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Qualifier("markPriceUrl")
    private final String markPriceUrl;


    @Override
    public List<TradingPair> getFullListing() {
        ResponseEntity<TradingPair[]> response = restTemplate.getForEntity(markPriceUrl, TradingPair[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public TradingPair getPair(String symbol) {
        ResponseEntity<TradingPair> response = restTemplate.getForEntity(
                markPriceUrl + "?symbol={symbol}",
                TradingPair.class,
                symbol);

        return response.getBody();
    }
}


