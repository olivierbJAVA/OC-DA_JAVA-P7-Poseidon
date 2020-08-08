package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TradeImplService implements ITradeService {

    @Autowired
    TradeRepository tradeRepository;

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade getTradeById(Integer id) {
        return tradeRepository.getOne(id);
    }

    @Override
    public Trade saveOrUpdate(Trade Trade) {
        return tradeRepository.save(Trade);
    }

    @Override
    public void delete (Integer id) {
        tradeRepository.deleteById(id);
    }
}
