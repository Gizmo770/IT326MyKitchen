package com.itproject.MyKitchenResources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itproject.MyKitchenResources.Entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    
}
