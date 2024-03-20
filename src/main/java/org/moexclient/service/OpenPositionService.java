package org.moexclient.service;


import org.moexclient.entity.ContractOpenPositions;
import org.moexclient.repository.OpenPositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;


@Service
public class OpenPositionService {

    @Autowired
    private OpenPositionsRepository openPositionsRepository;

    public List<ContractOpenPositions> getAll(int id, LocalDate date, int limit) {
        List<ContractOpenPositions> oneContractData =
                openPositionsRepository.getOneContractData(id, date, limit);
        return oneContractData;
    }

    public List<ContractOpenPositions> getAll2(LocalDate date) {
        List<ContractOpenPositions> manyContractsData =
                openPositionsRepository.getManyContractsData(date);
        return manyContractsData;
    }

}
