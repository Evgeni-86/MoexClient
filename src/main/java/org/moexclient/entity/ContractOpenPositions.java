package org.moexclient.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "openpositions")
@Entity
public class ContractOpenPositions {

    @EmbeddedId
    private ContractOpenPositionsKey key;
    private Integer juridical_long;
    private Integer juridical_short;
    private Integer physical_long;
    private Integer physical_short;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id", insertable = false, updatable = false)
    private Contract contract;


    public ContractOpenPositions(LocalDate date, int contractId, Integer juridical_long, Integer juridical_short,
                                 Integer physical_long, Integer physical_short) {
        this.key = new ContractOpenPositionsKey(date, contractId);
        this.juridical_long = juridical_long;
        this.juridical_short = juridical_short;
        this.physical_long = physical_long;
        this.physical_short = physical_short;
    }

    @Override
    public String toString() {
        return "{" +
                "instrument=" + contract.getContract_name() +
                ", date=" + key.getDate() +
                ", juridical_long=" + juridical_long +
                ", juridical_short=" + juridical_short +
                ", physical_long=" + physical_long +
                ", physical_short=" + physical_short +
                '}';
    }

    //-----------------------------------------------------
    @Getter
    @Setter
    @NoArgsConstructor
    @Embeddable
    public static class ContractOpenPositionsKey implements Serializable {
        private LocalDate date;
        private Integer contract_id;

        public ContractOpenPositionsKey(LocalDate date, Integer contract_id) {
            this.date = date;
            this.contract_id = contract_id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ContractOpenPositionsKey that = (ContractOpenPositionsKey) o;
            return Objects.equals(date, that.date) &&
                    Objects.equals(contract_id, that.contract_id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(date, contract_id);
        }
    }
}
