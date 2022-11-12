package com.roberto.pismo.transactions.persistence.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
@Builder
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transaction_ID")
    private Long id;

    @Column(name = "Account_ID", nullable = false)
    private Long accountID;

    @Column(name = "Operation_Type_ID", nullable = false)
    private Integer operationTypeID;

    @Column(name = "Amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "Event_Date", nullable = false)
    private LocalDateTime eventDate;

}
