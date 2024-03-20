package org.moexclient.web;

import org.moexclient.entity.ContractOpenPositions;
import org.moexclient.model.OpenPositionsByOne;
import org.moexclient.service.OpenPositionService;
import org.moexclient.util.updateData.DatabaseUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api/positions")
public class OpenPositionsRESTController {

    @Autowired
    private DatabaseUpdater databaseUpdater;
    @Autowired
    private OpenPositionService openPositionService;
    private static final DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");


    @GetMapping("/contract/{id}/{date}/{limit}")
    public OpenPositionsByOne getAll(@PathVariable(name = "id") int id,
                                     @PathVariable(name = "date") String date,
                                     @PathVariable(name = "limit") int limit) {
        return OpenPositionsByOne.toModel(openPositionService.getAll(id, LocalDate.parse(date), limit));
    }

    @GetMapping("/contracts/{date}")
    public List<ContractOpenPositions> getAll2(@PathVariable(name = "date") String date) {
        return openPositionService.getAll2(LocalDate.parse(date, inputDateFormatter));
    }

    @RequestMapping("/update")
    public void update() {
        try {
            databaseUpdater.updateDatabase();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
