package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TradeImplService implements ITradeService {

    @Autowired
    TradeRepository tradeRepository;

    public List<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade findTradeById(Integer id) throws RecordNotFoundException {
        Optional<Trade> trade = tradeRepository.findById(id);

        if(trade.isPresent()) {
            return trade.get();
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }

        //return tradeRepository.getOne(id);
    }

    @Override
    public Trade createTrade(Trade Trade) {
        return tradeRepository.save(Trade);
    }

    @Override
    public Trade updateTrade(Trade Trade) {
        return tradeRepository.save(Trade);
    }

    @Override
    public void deleteTrade (Integer id) throws RecordNotFoundException {
        Optional<Trade> trade = tradeRepository.findById(id);

        if(trade.isPresent()) {
            tradeRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }
        //tradeRepository.deleteById(id);
    }
}
