package com.drifter.spring6restmvc.services;

import guru.springframework.spring6restmvcapi.model.BeerDTO;
import guru.springframework.spring6restmvcapi.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beerDTO1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy CatTestIMPL")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12345")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        BeerDTO beerDTO2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("41231312")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        BeerDTO beerDTO3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("312321412")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        beerMap.put(beerDTO1.getId(), beerDTO1);
        beerMap.put(beerDTO2.getId(), beerDTO2);
        beerMap.put(beerDTO3.getId(), beerDTO3);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize){
        return new PageImpl<>(new ArrayList<>(beerMap.values()));
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer Id in service was called. Id {}", id.toString());

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {

        BeerDTO savedBeerDTO = BeerDTO.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .beerName(beerDTO.getBeerName())
                .beerStyle(beerDTO.getBeerStyle())
                .quantityOnHand(beerDTO.getQuantityOnHand())
                .upc(beerDTO.getUpc())
                .price(beerDTO.getPrice())
                .build();

        beerMap.put(savedBeerDTO.getId(), savedBeerDTO);

        return savedBeerDTO;
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerUuid, BeerDTO beerDTO) {
        BeerDTO fetchedBeerDTO = beerMap.get(beerUuid);
        fetchedBeerDTO.setBeerName(beerDTO.getBeerName());
        fetchedBeerDTO.setPrice(beerDTO.getPrice());
        fetchedBeerDTO.setUpc(beerDTO.getUpc());
        fetchedBeerDTO.setQuantityOnHand(beerDTO.getQuantityOnHand());
        return Optional.of(fetchedBeerDTO);
    }

    @Override
    public Boolean deleteById(UUID beerId) {
        beerMap.remove(beerId);

        return true;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerUuid, BeerDTO beerDTO) {
        BeerDTO fetchedBeerDTO = beerMap.get(beerUuid);

        if (StringUtils.hasText(beerDTO.getBeerName())) {
            fetchedBeerDTO.setBeerName(beerDTO.getBeerName());
        }

        if (beerDTO.getBeerStyle() != null) {
            fetchedBeerDTO.setBeerStyle(beerDTO.getBeerStyle());
        }

        if (beerDTO.getPrice() != null) {
            fetchedBeerDTO.setPrice(beerDTO.getPrice());
        }

        if (beerDTO.getQuantityOnHand() != null) {
            fetchedBeerDTO.setQuantityOnHand(beerDTO.getQuantityOnHand());
        }

        if (StringUtils.hasText(beerDTO.getUpc())) {
            fetchedBeerDTO.setUpc(beerDTO.getUpc());
        }

        return Optional.of(fetchedBeerDTO);
    }
}
