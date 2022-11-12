package com.roberto.pismo.transactions.persistence.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Operations_Types")
@Data
public class OperationTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Operation_Type_ID")
    private Integer id;

    @Column(name = "Description", nullable = false)
    private String description;

}
