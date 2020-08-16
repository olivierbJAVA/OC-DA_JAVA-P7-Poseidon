package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IBidListService {

    List<BidList> findAllBidLists();

    BidList findBidListById(Integer id) throws ResourceNotFoundException;

    BidList createBidList(BidList bidList);

    BidList updateBidList(BidList bidList) throws ResourceNotFoundException;

    void deleteBidListById(Integer id) throws ResourceNotFoundException;
}
