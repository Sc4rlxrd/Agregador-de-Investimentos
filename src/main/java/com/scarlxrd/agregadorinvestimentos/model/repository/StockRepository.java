package com.scarlxrd.agregadorinvestimentos.model.repository;

import com.scarlxrd.agregadorinvestimentos.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,String> {
}
