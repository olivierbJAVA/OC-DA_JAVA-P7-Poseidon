package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * Interface to implement for managing the services for Trade entities.
 */
public interface ITradeService {

    /**
     * Return all Trades.
     *
     * @return The list of all Trades
     */
    List<Trade> findAllTrades();

    /**
     * Return a Trade given its id.
     *
     * @param id The id of the Trade
     * @return The Trade corresponding to the id
     * @throws ResourceNotFoundException if no Trade is found for the given id
     */
    Trade findTradeById(Integer id) throws ResourceNotFoundException;

    /**
     * Create a Trade.
     *
     * @param trade The Trade to create
     * @return The Trade created
     */
    Trade createTrade(Trade trade);

    /**
     * Update a Trade.
     *
     * @param trade The Trade to update
     * @return The Trade updated
     * @throws ResourceNotFoundException if the Trade to update does not exist
     */
    Trade updateTrade(Trade trade) throws ResourceNotFoundException;

    /**
     * Delete a Trade.
     *
     * @param id The id of the Trade
     * @throws ResourceNotFoundException if the Trade to delete does not exist
     */
    void deleteTradeById(Integer id) throws ResourceNotFoundException;
}
