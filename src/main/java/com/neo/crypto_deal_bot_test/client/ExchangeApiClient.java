package com.neo.crypto_deal_bot_test.client;

import com.neo.crypto_deal_bot_test.domain.TradingPair;

import java.util.List;


public interface ExchangeApiClient {

    public List<TradingPair> getFullListing();

    public TradingPair getPair(String symbol);

}
