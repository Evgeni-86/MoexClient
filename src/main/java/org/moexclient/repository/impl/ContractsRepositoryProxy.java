package org.moexclient.repository.impl;

import org.moexclient.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContractsRepositoryProxy extends JpaRepository<Contract, Integer> {
}
