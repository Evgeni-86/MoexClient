package org.moexclient.repository.impl;

import org.moexclient.entity.ContractOpenPositions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OpenPositionsRepositoryProxy extends JpaRepository<ContractOpenPositions, ContractOpenPositions.ContractOpenPositionsKey> {
}
