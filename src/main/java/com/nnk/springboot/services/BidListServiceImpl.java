package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BidListServiceImpl implements IBidListService {

    @Autowired
    BidListRepository bidListRepository;

    public List<BidList> findAllBidLists() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList findBidListById(Integer id) throws ResourceNotFoundException {
        return bidListRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    @Override
    public BidList createBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public BidList updateBidList(BidList bidList) {
        bidListRepository.findById(bidList.getBidListId()).orElseThrow(()-> new ResourceNotFoundException(bidList.getBidListId()));
        return bidListRepository.save(bidList);
    }

    @Override
    public void deleteBidListById(Integer id) throws ResourceNotFoundException {
        bidListRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        bidListRepository.deleteById(id);
    }
}
