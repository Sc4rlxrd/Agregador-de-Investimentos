package com.scarlxrd.agregadorinvestimentos.model.repository;

import com.scarlxrd.agregadorinvestimentos.model.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
