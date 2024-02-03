package com.scarlxrd.agregadorinvestimentos.model.repository;

import com.scarlxrd.agregadorinvestimentos.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
