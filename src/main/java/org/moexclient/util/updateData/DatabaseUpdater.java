package org.moexclient.util.updateData;


import org.moexclient.entity.Contract;
import org.moexclient.entity.ContractOpenPositions;
import org.moexclient.repository.ContractsRepository;
import org.moexclient.repository.OpenPositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.concurrent.*;


@EnableScheduling
@Component
public class DatabaseUpdater {

    @Autowired
    private OpenPositionsRepository openPositionsRepository;
    @Autowired
    private ContractsRepository contractsRepository;
    @Autowired
    private Request request;
    private final LocalDate endDate;
    private final LocalDate startDate;


    public DatabaseUpdater() {
        endDate = LocalDate.now().minusDays(1);
        startDate = endDate.minusDays(100);
    }

    public void updateDatabase() throws InterruptedException {
        List<Contract> contractList = contractsRepository.findAll();
        if (contractList.isEmpty()) return;

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<?>> futures = new ArrayList<>();

        for (Contract contract : contractList) {
            Future<?> future = executorService.submit(() -> {
                update(contract);
            });
            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                System.out.println("Exception occurred, retrying task...");
                Contract contract = contractList.get(futures.indexOf(future));
                executorService.submit(() -> update(contract));
            }
        }

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("DONE!!!");
    }

    private void update(Contract contract) {
        for (LocalDate currentDate = startDate; !currentDate.isAfter(endDate); currentDate = currentDate.plusDays(1)) {

            if (currentDate.getDayOfWeek().getValue() >= 6) continue;

            ContractOpenPositions.ContractOpenPositionsKey key =
                    new ContractOpenPositions.ContractOpenPositionsKey(currentDate, contract.getId());

            if (openPositionsRepository.existsById(key)) {
                System.out.printf("id = %s date = %s : isPresent in DB\n", contract.getContract_name(), currentDate);
                continue;
            }

            ContractOpenPositions contractOpenPositions = request.doRequest(contract, currentDate);
            if (contractOpenPositions != null) {
                openPositionsRepository.save(contractOpenPositions);
                System.out.printf("id = %s date = %s : write to DB\n",
                        contractOpenPositions.getKey().getContract_id(), contractOpenPositions.getKey().getDate());
            }
        }
    }
}
