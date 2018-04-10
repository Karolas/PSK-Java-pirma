package psk.Hibernate.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOUNTS")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "SELECT c FROM Account c"),
        @NamedQuery(name = "Account.count", query = "SELECT count(c) FROM Account c"),
        @NamedQuery(name = "Account.accountNrSel", query = "SELECT max(c.accountNr) from Account c")
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
    @Digits(integer = 10, fraction = 0)
    private BigDecimal accountNr;

    @Column(name = "BALANCE")
    @Digits(integer = 15, fraction = 2)
    private BigDecimal balance;

    @OneToMany(mappedBy = "fromAccount")
    private List<Transaction> transactions = new ArrayList<>();
}
