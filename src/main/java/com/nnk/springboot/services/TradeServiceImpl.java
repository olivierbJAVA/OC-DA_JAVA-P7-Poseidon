package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TradeServiceImpl implements ITradeService {

    @Autowired
    TradeRepository tradeRepository;

    public List<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade findTradeById(Integer id) throws ResourceNotFoundException {
        return tradeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id, "Trade"));
    }

    @Override
    public Trade createTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public Trade updateTrade(Trade trade) {
        tradeRepository.findById(trade.getTradeId()).orElseThrow(()-> new ResourceNotFoundException(trade.getTradeId(), "Trade"));
        return tradeRepository.save(trade);
    }

    @Override
    public void deleteTradeById(Integer id) throws ResourceNotFoundException {
        tradeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id, "Trade"));
        tradeRepository.deleteById(id);
    }
}
