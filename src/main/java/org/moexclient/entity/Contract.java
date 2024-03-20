package org.moexclient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Table(name = "contracts")
@Entity
public class Contract {
    @Id
    private Integer id;
    private String contract_name;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "contract", fetch = FetchType.LAZY)
    private List<ContractOpenPositions> openPositionsList;
}
