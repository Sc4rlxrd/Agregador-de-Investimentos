package com.scarlxrd.agregadorinvestimentos.model.service;

import com.scarlxrd.agregadorinvestimentos.model.client.BrapiClient;
import com.scarlxrd.agregadorinvestimentos.model.client.dto.BrapiResponseDto;
import com.scarlxrd.agregadorinvestimentos.model.controller.dto.AccountStockResponseDto;
import com.scarlxrd.agregadorinvestimentos.model.controller.dto.AssociateAccountStockDto;
import com.scarlxrd.agregadorinvestimentos.model.entity.AccountStock;
import com.scarlxrd.agregadorinvestimentos.model.entity.AccountStockId;
import com.scarlxrd.agregadorinvestimentos.model.repository.AccountRepository;
import com.scarlxrd.agregadorinvestimentos.model.repository.AccountStockRepository;
import com.scarlxrd.agregadorinvestimentos.model.repository.StockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Value("#{environment.TOKEN}")
    private String TOKEN;
    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;
    private BrapiClient brapiClient;

    public AccountService(AccountRepository accountRepository,
                          StockRepository stockRepository,
                          AccountStockRepository accountStockRepository,
                          BrapiClient brapiClient) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
        this.brapiClient = brapiClient;
    }

    public void associateStock(String accountId, AssociateAccountStockDto dto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // DTO -> ENTITY
        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );

        accountStockRepository.save(entity);

    }

    public List<AccountStockResponseDto> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return account.getAccountStocks()
                .stream()
                .map(as ->
                        new AccountStockResponseDto(
                                as.getStock().getStockId(),
                                as.getQuantity(),
                                getTotal(as.getQuantity(), as.getStock().getStockId())
                        ))
                .toList();
    }

    private double getTotal(Integer quantity, String stockId) {

        BrapiResponseDto response = brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().getFirst().regularMarketPrice();

        return quantity * price;
    }


}
