package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class in charge of managing the services for the Trades.
 */
@Service
@Transactional
public class TradeServiceImpl implements ITradeService {

    @Autowired
    TradeRepository tradeRepository;

    /**
     * Return all Trades.
     *
     * @return The list of all Trades
     */
    public List<Trade> findAllTrades() {
        return tradeRepository.findAll();
    }

    /**
     * Return a Trade given its id.
     *
     * @param id The id of the Trade
     * @return The Trade corresponding to the id
     * A ResourceNotFoundException is thrown if no Trade is found for the given id
     */
    @Override
    public Trade findTradeById(Integer id) throws ResourceNotFoundException {
        return tradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Trade"));
    }

    /**
     * Create a Trade.
     *
     * @param trade The Trade to create
     * @return The Trade created
     */
    @Override
    public Trade createTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Update a Trade.
     *
     * @param trade The Trade to update
     * @return The Trade updated
     * A ResourceNotFoundException is thrown if the Trade to update does not exist
     */
    @Override
    public Trade updateTrade(Trade trade) {
        tradeRepository.findById(trade.getTradeId()).orElseThrow(() -> new ResourceNotFoundException(trade.getTradeId(), "Trade"));
        return tradeRepository.save(trade);
    }

    /**
     * Delete a Trade.
     *
     * @param id The id of the Trade
     * A ResourceNotFoundException is thrown if the Trade to delete does not exist
     */
    @Override
    public void deleteTradeById(Integer id) throws ResourceNotFoundException {
        tradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Trade"));
        tradeRepository.deleteById(id);
    }
}
