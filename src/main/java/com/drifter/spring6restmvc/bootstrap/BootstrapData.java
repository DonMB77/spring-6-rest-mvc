package com.drifter.spring6restmvc.bootstrap;

import com.drifter.spring6restmvc.entities.Beer;
import com.drifter.spring6restmvc.entities.BeerOrder;
import com.drifter.spring6restmvc.entities.BeerOrderLine;
import com.drifter.spring6restmvc.entities.Customer;
import com.drifter.spring6restmvc.model.BeerCSVRecord;
import com.drifter.spring6restmvc.repositories.BeerOrderRepository;
import com.drifter.spring6restmvc.repositories.BeerRepository;
import com.drifter.spring6restmvc.repositories.CustomerRepository;
import com.drifter.spring6restmvc.services.BeerCsvService;
import guru.springframework.spring6restmvcapi.model.BeerStyle;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;
    private final BeerCsvService beerCsvService;
    private final BeerOrderRepository beerOrderRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //clearDataOfBeerOrderRepo();
        loadBeerData();
        loadCsvData();
        loadCustomerData();
        loadOrderData();
        //clearDataOfBeerRepo();

        System.out.println("BEER REPO COUNT: " + beerRepository.count());
        System.out.println("BEER REPO COUNT: " + customerRepository.count());
    }

    private void clearDataOfBeerRepo() {
        beerRepository.deleteAll();
    }

    private void clearDataOfBeerOrderRepo() {
        beerOrderRepository.deleteAll();
    }

    private void loadOrderData() {
        val customersList = customerRepository.findAll();
        val beersList = beerRepository.findAll();

        val beerIterator = beersList.iterator();

        customersList.forEach(customer -> {
            Beer beer1 = beerIterator.next();
            Beer beer2 = beerIterator.next();

            val beerOrder = BeerOrder.builder()
                    .customer(customer)
                    .beerOrderLines(Set.of(
                            BeerOrderLine.builder()
                                    .beer(beer1)
                                    .orderQuantity(1)
                                    .build(),
                            BeerOrderLine.builder()
                                    .beer(beer2)
                                    .orderQuantity(1)
                                    .build()
                    ))
                    .build();
            beerOrderRepository.save(beerOrder);
        });

        val orders = beerOrderRepository.findAll();

    }

    private void loadCsvData() throws FileNotFoundException {
        if (beerRepository.count() < 10){
            File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");

            List<BeerCSVRecord> recs = beerCsvService.convertCSV(file);

            recs.forEach(beerCSVRecord -> {
                BeerStyle beerStyle = switch (beerCSVRecord.getStyle()) {
                    case "American Pale Lager" -> BeerStyle.LAGER;
                    case "American Pale Ale (APA)", "American Black Ale", "Belgian Dark Ale", "American Blonde Ale" ->
                            BeerStyle.ALE;
                    case "American IPA", "American Double / Imperial IPA", "Belgian IPA" -> BeerStyle.IPA;
                    case "American Porter" -> BeerStyle.PORTER;
                    case "Oatmeal Stout", "American Stout" -> BeerStyle.STOUT;
                    case "Saison / Farmhouse Ale" -> BeerStyle.SAISON;
                    case "Fruit / Vegetable Beer", "Winter Warmer", "Berliner Weissbier" -> BeerStyle.WHEAT;
                    case "English Pale Ale" -> BeerStyle.PALE_ALE;
                    default -> BeerStyle.PILSNER;
                };

                beerRepository.save(Beer.builder()
                        .beerName(StringUtils.abbreviate(beerCSVRecord.getBeer(), 50))
                        .beerStyle(beerStyle)
                        .price(BigDecimal.TEN)
                        .upc(beerCSVRecord.getRow().toString())
                        .quantityOnHand(beerCSVRecord.getCount())
                        .build());
            });
        }
    }

    private void loadBeerData() {
        if (beerRepository.count() == 0) {
            Beer beer1 = Beer.builder()
                    .beerName("Galaxy CatTEST")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("12345")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(122)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            Beer beer2 = Beer.builder()
                    .beerName("Crank")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("41231312")
                    .price(new BigDecimal("11.99"))
                    .quantityOnHand(392)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            Beer beer3 = Beer.builder()
                    .beerName("Sunshine City")
                    .beerStyle(BeerStyle.IPA)
                    .upc("312321412")
                    .price(new BigDecimal("13.99"))
                    .quantityOnHand(144)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            beerRepository.save(beer1);
            beerRepository.save(beer2);
            beerRepository.save(beer3);

            beerRepository.saveAll(Arrays.asList(beer1, beer2, beer3));
        }
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {
            Customer customer1 = Customer.builder()
                    .version(1)
                    .costumerName("customer1")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Customer customer2 = Customer.builder()
                    .version(1)
                    .costumerName("customer2")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            Customer customer3 = Customer.builder()
                    .version(1)
                    .costumerName("customer3")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();

            customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
        }
    }
}
