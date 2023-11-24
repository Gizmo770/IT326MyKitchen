package com.it326.mykitchenresources.dbs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it326.mykitchenresources.entities.Fridge;

@Repository
public interface FridgeDb extends JpaRepository<Fridge, Long> {

}
