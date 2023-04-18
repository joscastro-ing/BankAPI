package com.bankapi.bankapi.Model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Accounts")
@SequenceGenerator(name="seq_gen", sequenceName = "seq_gen", initialValue = 103304112)
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "seq_gen")
    @Column(unique=true, nullable=false)
    private long accountNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_account")
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Type_accountId")
    private TypeAccount typeAcc;
    private double balance;


    
}
