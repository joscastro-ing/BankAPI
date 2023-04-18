package com.bankapi.bankapi.Model;
import javax.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TypesAccounts")
public class TypeAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "Type_id", unique=true, nullable=false)
    private Integer id;
    @Column(name= "Type", unique=true, nullable=false)
    private String type;
}
