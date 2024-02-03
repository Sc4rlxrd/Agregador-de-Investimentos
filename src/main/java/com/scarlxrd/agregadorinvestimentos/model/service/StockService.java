package com.scarlxrd.agregadorinvestimentos.model.service;

import com.scarlxrd.agregadorinvestimentos.model.controller.dto.CreateStockDto;
import com.scarlxrd.agregadorinvestimentos.model.entity.Stock;
import com.scarlxrd.agregadorinvestimentos.model.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {

        // DTO -> ENTITY
        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );

        stockRepository.save(stock);
    }
}
