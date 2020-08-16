package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITradeService {

    List<Trade> findAllTrades();

    Trade findTradeById(Integer id) throws ResourceNotFoundException;

    Trade createTrade(Trade trade);

    Trade updateTrade(Trade trade);

    void deleteTradeById(Integer id) throws ResourceNotFoundException;
}
