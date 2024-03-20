package org.moexclient.repository.impl;

import org.moexclient.entity.Contract;
import org.moexclient.repository.ContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ContractsRepositoryImpl implements ContractsRepository {

     @Autowired
     private ContractsRepositoryProxy contractsRepositoryProxy;

     @Override
     public List<Contract> findAll() {
          return contractsRepositoryProxy.findAll();
     }
}
