package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BidListImplService implements IBidListService {

    @Autowired
    BidListRepository bidListRepository;

    public List<BidList> findAllBidLists() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList findBidListById(Integer id) throws RecordNotFoundException {
        Optional<BidList> bidList = bidListRepository.findById(id);

        if(bidList.isPresent()) {
            return bidList.get();
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }

        //return bidListRepository.getOne(id);
    }

    @Override
    public BidList createBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public BidList updateBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public void deleteBidList(Integer id) throws RecordNotFoundException {
        Optional<BidList> bidList = bidListRepository.findById(id);

        if(bidList.isPresent()) {
            bidListRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }

        //bidListRepository.deleteById(id);
    }
}
