package com.neo.crypto_deal_bot_test.controller;

import com.neo.crypto_deal_bot_test.service.TradingPairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/fapi/v1")
@RequiredArgsConstructor
public class TradingPairController {

    private final TradingPairService tradingPairService;

    @GetMapping("/price")
    public ResponseEntity<BigDecimal> getPairPrice(@RequestParam("symbol") String symbol) {
        tradingPairService.fetchAndUpdatePrice(symbol);
        return ResponseEntity.ok(this.tradingPairService.getCurrentPrice(symbol));
    }
}
