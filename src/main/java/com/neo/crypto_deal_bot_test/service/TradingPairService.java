package com.neo.crypto_deal_bot_test.service;

import com.neo.crypto_deal_bot_test.client.ExchangeApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Service
@RequiredArgsConstructor
public class TradingPairService {
    private final ExchangeApiClient exchangeApiClient;
    private Map<String, BigDecimal> priceStorage = new ConcurrentHashMap<>();

    @Async("asyncTaskExecutor")
    public CompletableFuture<BigDecimal> fetchAndUpdatePrice(String symbol) {
        log.info("Updating price storage in Thread: {}", Thread.currentThread().getName());
        BigDecimal price = exchangeApiClient.getPair(symbol).getMarkPrice();
        if (price != null) {
            priceStorage.put(symbol, price);
            log.info("Price storage updated with actual price for: {}", symbol);
            return CompletableFuture.completedFuture(price);
        }
        return CompletableFuture.completedFuture(null);
    }

    public BigDecimal getCurrentPrice(String symbol) {
        BigDecimal price = priceStorage.get(symbol);
        if (price != null) {
            log.info("Price for {} was sent to user", symbol);
            return price;
        } else {
            log.info("Price storage no have price for {}, sent request to Binance to get actual price", symbol);
            CompletableFuture<BigDecimal> futurePrice = fetchAndUpdatePrice(symbol);
            try {
                return futurePrice.get();
            } catch (Exception e) {
                log.error("Got exception {} in attempt to get actual price of {}", e.getMessage(), symbol);
                return null;
            }
        }
    }
}
