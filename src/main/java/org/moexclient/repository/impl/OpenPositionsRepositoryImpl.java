package org.moexclient.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.moexclient.entity.ContractOpenPositions;
import org.moexclient.repository.OpenPositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class OpenPositionsRepositoryImpl implements OpenPositionsRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private OpenPositionsRepositoryProxy openPositionsRepositoryProxy;


    @Override
    public boolean existsById(ContractOpenPositions.ContractOpenPositionsKey key) {
        return openPositionsRepositoryProxy.existsById(key);
    }

    @Override
    public ContractOpenPositions save(ContractOpenPositions contractOpenPositions) {
        return openPositionsRepositoryProxy.save(contractOpenPositions);
    }

    //TODO индекс по дате?
    @Override
    public List<ContractOpenPositions> getOneContractData(int id, LocalDate date, int limit) {
        List<ContractOpenPositions> openPositions = entityManager.createQuery(
                        "FROM ContractOpenPositions WHERE key.contract_id = :id " +
                                "AND key.date <= :d ORDER BY key.date DESC", ContractOpenPositions.class)
                .setParameter("id", id)
                .setParameter("d", date)
                .setMaxResults(limit)
                .getResultList();
        return openPositions;
    }

    @Override
    public List<ContractOpenPositions> getManyContractsData(LocalDate date) {

        TypedQuery<LocalDate> query = entityManager.createQuery(
                "SELECT key.date FROM ContractOpenPositions WHERE key.date <= :d ORDER BY key.date DESC", LocalDate.class);

        query.setParameter("d", date);
        query.setMaxResults(1);
        LocalDate lastDataDate = query.getSingleResult();

        List<ContractOpenPositions> openPositions = entityManager.createQuery(
                        "FROM ContractOpenPositions c JOIN FETCH c.contract WHERE c.key.date = :last ORDER BY c.contract.contract_name",
                        ContractOpenPositions.class)
                .setParameter("last", lastDataDate)
                .getResultList();

        return openPositions;
    }
}
