package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class in charge of managing the services for BidList entities.
 */
@Service
@Transactional
public class BidListServiceImpl implements IBidListService {

    @Autowired
    BidListRepository bidListRepository;

    /**
     * Return all BidLists.
     *
     * @return The list of all BidLists
     */
    public List<BidList> findAllBidLists() {
        return bidListRepository.findAll();
    }

    /**
     * Find a BidList given its id.
     *
     * @param id The id of the BidList
     * @return The BidList corresponding to the id
     * @throws ResourceNotFoundException if no BidList is found for the given id
     */
    @Override
    public BidList findBidListById(Integer id) throws ResourceNotFoundException {
        return bidListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "BidList"));
    }

    /**
     * Create a BidList.
     *
     * @param bidList The BidList to create
     * @return The BidList created
     */
    @Override
    public BidList createBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * Update a BidList.
     *
     * @param bidList The BidList to update
     * @return The BidList updated
     * @throws ResourceNotFoundException if the BidList to update does not exist
     */
    @Override
    public BidList updateBidList(BidList bidList) {
        bidListRepository.findById(bidList.getBidListId()).orElseThrow(() -> new ResourceNotFoundException(bidList.getBidListId(), "BidList"));
        return bidListRepository.save(bidList);
    }

    /**
     * Delete a BidList.
     *
     * @param id The id of the BidList
     * @throws ResourceNotFoundException if the BidList to delete does not exist
     */
    @Override
    public void deleteBidListById(Integer id) throws ResourceNotFoundException {
        bidListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "BidList"));
        bidListRepository.deleteById(id);
    }
}
