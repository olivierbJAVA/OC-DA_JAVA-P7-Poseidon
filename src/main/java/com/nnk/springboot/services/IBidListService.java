package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface IBidListService {

    List<BidList> getAllBidLists();

    BidList getBidListById(Integer id);

    BidList saveBidList(BidList bidList);

    BidList updateBidList(BidList bidList);

    void deleteBidList(Integer id);
}
