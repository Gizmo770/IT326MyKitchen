package com.it326.mykitchenresources.dbs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it326.mykitchenresources.entities.Account;

@Repository
public interface AccountDb extends JpaRepository<Account, Integer> {

    public Account findByAccountId(Integer accountId);

    public Account findByUsername(String userName);

    public void deleteById(Integer accountId);
}
