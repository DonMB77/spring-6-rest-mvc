package com.drifter.spring6restmvc.controller;

import com.drifter.spring6restmvc.entities.BeerOrder;
import com.drifter.spring6restmvc.services.BeerOrderService;
import guru.springframework.spring6restmvcapi.model.BeerOrderCreateDTO;
import guru.springframework.spring6restmvcapi.model.BeerOrderDTO;
import guru.springframework.spring6restmvcapi.model.BeerOrderUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class BeerOrderController {

    public static final String BEER_ORDER_PATH = "/api/v1/beerorder";
    public static final String BEER_ORDER_PATH_ID = BEER_ORDER_PATH + "/{beerOrderId}";

    private final BeerOrderService beerOrderService;

    @GetMapping(BEER_ORDER_PATH)
    public Page<BeerOrderDTO> listBeerOrders(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {

        return beerOrderService.listBeerOrders(pageNumber, pageSize);
    }

    @GetMapping(BEER_ORDER_PATH_ID)
    public BeerOrderDTO getBeerOrderById(@PathVariable("beerOrderId") UUID beerOrderId) {

        return beerOrderService.getBeerOrderById(beerOrderId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(BEER_ORDER_PATH)
    public ResponseEntity<Void> handlePost(@Validated @RequestBody BeerOrderCreateDTO beerOrderCreateDTO) {

        BeerOrder savedOrder = beerOrderService.saveNewBeerOrder(beerOrderCreateDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_ORDER_PATH_ID + "/" + savedOrder.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(BEER_ORDER_PATH_ID)
    public ResponseEntity<BeerOrderDTO> handlePatch(@RequestBody BeerOrderUpdateDTO beerOrderUpdateDTO, @PathVariable("beerOrderId") UUID beerOrderId) {
        return ResponseEntity.ok(beerOrderService.updateOrder(beerOrderId, beerOrderUpdateDTO));
    }

    @DeleteMapping(BEER_ORDER_PATH_ID)
    public ResponseEntity<Void> handleDelete(@PathVariable("beerOrderId") UUID beerOrderId) {
        beerOrderService.deleteOrderById(beerOrderId);
        return ResponseEntity.noContent().build();
    }
}
