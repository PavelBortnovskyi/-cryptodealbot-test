package com.neo.crypto_deal_bot_test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TradingPair {

    private String symbol;

    private BigDecimal markPrice;

    private BigDecimal indexPrice;

    private BigDecimal estimatedSettlePrice;

    private Double lastFundingRate;

    private Double interestRate;

//    private LocalDateTime nextFundingTime;
//
//    private LocalDateTime time;
}
