package org.moexclient.repository;

import org.moexclient.entity.Contract;

import java.util.List;

public interface ContractsRepository {
    public List<Contract> findAll();
}
