package Hibernate.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
@EqualsAndHashCode(of = "customerNr")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CUSTOMERNR")
    private DecimalFormat customerNr;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @OneToMany(mappedBy ="customer")
    private List<Transaction> transactions = new ArrayList<Transaction>();

    @ManyToMany
    @JoinTable(name = "CUSTOMERS_ACCOUNTS")
    private
    List<Account> accounts = new ArrayList<Account>();
}
