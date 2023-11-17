package com.it326.mykitchenresources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDb extends JpaRepository<Account, Integer> {
    
}
