package com.drifter.spring6restmvc.services;

import com.drifter.spring6restmvc.entities.BeerOrder;
import com.drifter.spring6restmvc.mappers.BeerOrderMapper;
import com.drifter.spring6restmvc.model.BeerOrderDTO;
import com.drifter.spring6restmvc.repositories.BeerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerOrderServiceImpl implements BeerOrderService {

    private Map<UUID, BeerOrderDTO> beerOrderMap;

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;


    private static final  int DEFAULT_PAGE = 0;
    private static final  int DEFAULT_PAGE_SIZE = 25;

    @Override
    public Page<BeerOrderDTO> listBeerOrders(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<BeerOrder> beerOrderPage;

        beerOrderPage = beerOrderRepository.findAll(pageRequest);


        return beerOrderPage.map(beerOrderMapper::beerOrderToBeerOrderDto);

    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }

        return PageRequest.of(queryPageNumber, queryPageSize);
    }

    @Override
    public Optional<BeerOrderDTO> getBeerOrderById(UUID id) {
        return Optional.ofNullable(beerOrderMapper.beerOrderToBeerOrderDto(beerOrderRepository.findById(id)
                .orElse(null)));
    }
}
