package com.bankapi.bankapi.Model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    public int idTransaction;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="source_account_fk")
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="deposit_account_fk")
    private Account depositAccount;

    private double amount;
    @CreationTimestamp
    private LocalDateTime date;
    private String concept;

    @Override
    public String toString(){
        return "\ntransactionId: " + this.idTransaction +
        "\n source account: " + this.sourceAccount.getAccountNumber() +
        "\n deposit account: " + this.depositAccount.getAccountNumber() +
        "\n amount: $" + this.amount +
        "\n date: " + this.date +
        "\n Concept: " + ((this.concept == null)? "empty": this.concept);
    }
}
