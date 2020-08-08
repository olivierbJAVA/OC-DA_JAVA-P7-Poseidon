package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BidListImplService implements IBidListService {

    @Autowired
    BidListRepository bidListRepository;

    public List<BidList> getAllBidLists() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList getBidListById(Integer id) {
        return bidListRepository.getOne(id);
    }

    @Override
    public BidList saveOrUpdate(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public void delete (Integer id) {
        bidListRepository.deleteById(id);
    }
}
