package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.RecordNotFoundException;

import java.util.List;

public interface ITradeService {

    List<Trade> findAllTrades();

    Trade findTradeById(Integer id) throws RecordNotFoundException;

    Trade createTrade(Trade trade);

    Trade updateTrade(Trade trade);

    void deleteTradeById(Integer id) throws RecordNotFoundException;
}
