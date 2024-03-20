package org.moexclient.model;


import org.moexclient.entity.ContractOpenPositions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OpenPositionsByOne {
    private int contract_id;
    private String contract_name;
    private List<String> dates = new ArrayList<>();
    private List<Integer> juridical_long = new ArrayList<>();
    private List<Integer> juridical_short = new ArrayList<>();
    private List<Integer> physical_long = new ArrayList<>();
    private List<Integer> physical_short = new ArrayList<>();

    public static OpenPositionsByOne toModel(List<ContractOpenPositions> openPositionsList) {
        Collections.reverse(openPositionsList);
        OpenPositionsByOne openPositionsByOne = new OpenPositionsByOne();
        openPositionsByOne.contract_id = openPositionsList.get(0).getContract().getId();
        openPositionsByOne.contract_name = openPositionsList.get(0).getContract().getContract_name();
        for (ContractOpenPositions curOpenPosition : openPositionsList) {
            openPositionsByOne.dates.add(curOpenPosition.getKey().getDate().toString());
            openPositionsByOne.juridical_long.add(curOpenPosition.getJuridical_long());
            openPositionsByOne.juridical_short.add(curOpenPosition.getJuridical_short());
            openPositionsByOne.physical_long.add(curOpenPosition.getPhysical_long());
            openPositionsByOne.physical_short.add(curOpenPosition.getPhysical_short());
        }
        return openPositionsByOne;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<Integer> getJuridical_long() {
        return juridical_long;
    }

    public void setJuridical_long(List<Integer> juridical_long) {
        this.juridical_long = juridical_long;
    }

    public List<Integer> getJuridical_short() {
        return juridical_short;
    }

    public void setJuridical_short(List<Integer> juridical_short) {
        this.juridical_short = juridical_short;
    }

    public List<Integer> getPhysical_long() {
        return physical_long;
    }

    public void setPhysical_long(List<Integer> physical_long) {
        this.physical_long = physical_long;
    }

    public List<Integer> getPhysical_short() {
        return physical_short;
    }

    public void setPhysical_short(List<Integer> physical_short) {
        this.physical_short = physical_short;
    }
}
