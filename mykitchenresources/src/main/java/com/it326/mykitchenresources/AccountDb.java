package com.it326.mykitchenresources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it326.mykitchenresources.Account;

@Repository
public interface AccountDb extends JpaRepository<Account, Integer> {
    
}
