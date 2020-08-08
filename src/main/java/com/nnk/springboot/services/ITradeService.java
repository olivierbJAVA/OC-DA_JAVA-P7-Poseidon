package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

import java.util.List;

public interface ITradeService {

    List<Trade> getAllTrades();

    Trade getTradeById(Integer id);

    Trade saveOrUpdate(Trade trade);

    void delete (Integer id);

}
