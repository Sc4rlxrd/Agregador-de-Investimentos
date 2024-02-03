package com.scarlxrd.agregadorinvestimentos.model.repository;

import com.scarlxrd.agregadorinvestimentos.model.entity.AccountStock;
import com.scarlxrd.agregadorinvestimentos.model.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
