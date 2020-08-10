package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.RecordNotFoundException;

import java.util.List;

public interface IBidListService {

    List<BidList> findAllBidLists();

    BidList findBidListById(Integer id) throws RecordNotFoundException;

    BidList createBidList(BidList bidList);

    BidList updateBidList(BidList bidList);

    void deleteBidListById(Integer id) throws RecordNotFoundException;
}
