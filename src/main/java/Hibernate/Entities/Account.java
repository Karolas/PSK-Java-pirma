package Hibernate.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;

@Entity
@Table(name = "ACCOUNTS")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "SELECT c FROM Account c"),
        @NamedQuery(name = "Account.count", query = "SELECT count(c) FROM Account c")
})
@Getter
@Setter
@EqualsAndHashCode(of = "accountNr")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ACCOUNTNR")
    private DecimalFormat accountNr;

    @Column(name = "BALANCE")
    private DecimalFormat balance;
}
