package com.itproject.MyKitchenResources.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itproject.MyKitchenResources.Entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    
}
