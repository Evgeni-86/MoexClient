package org.moexclient.repository;


import org.moexclient.entity.ContractOpenPositions;

import java.time.LocalDate;
import java.util.List;

public interface OpenPositionsRepository {
    public boolean existsById(ContractOpenPositions.ContractOpenPositionsKey key);

    public ContractOpenPositions save(ContractOpenPositions contractOpenPositions);

    public List<ContractOpenPositions> getOneContractData(int id, LocalDate date, int limit);

    List<ContractOpenPositions> getManyContractsData(LocalDate date);
}
