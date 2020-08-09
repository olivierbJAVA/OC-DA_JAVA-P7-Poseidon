package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

import java.util.List;

public interface ITradeService {

    List<Trade> getAllTrades();

    Trade getTradeById(Integer id);

    Trade saveTrade(Trade trade);

    Trade updateTrade(Trade trade);

    void deleteTrade(Integer id);
}
