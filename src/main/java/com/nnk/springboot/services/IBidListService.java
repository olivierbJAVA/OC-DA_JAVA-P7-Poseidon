package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * Interface to implement for managing the services for BidList entities.
 */
public interface IBidListService {

    /**
     * Return all BidLists.
     *
     * @return The list of all BidLists
     */
    List<BidList> findAllBidLists();

    /**
     * Find a BidList given its id.
     *
     * @param id The id of the BidList
     * @return The BidList corresponding to the id
     * @throws ResourceNotFoundException if no BidList is found for the given id
     */
    BidList findBidListById(Integer id) throws ResourceNotFoundException;

    /**
     * Create a BidList.
     *
     * @param bidList The BidList to create
     * @return The BidList created
     */
    BidList createBidList(BidList bidList);

    /**
     * Update a BidList.
     *
     * @param bidList The BidList to update
     * @return The BidList updated
     * @throws ResourceNotFoundException if the BidList to update does not exist
     */
    BidList updateBidList(BidList bidList) throws ResourceNotFoundException;

    /**
     * Delete a BidList.
     *
     * @param id The id of the BidList
     * @throws ResourceNotFoundException if the BidList to delete does not exist
     */
    void deleteBidListById(Integer id) throws ResourceNotFoundException;
}
