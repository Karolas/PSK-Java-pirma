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
@Table(name = "CUSTOMERS")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
})
@EqualsAndHashCode(of = "customerNr")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "CUSTOMERNR")
    @Digits(integer = 12, fraction = 0)
    @Getter
    @Setter
    private BigDecimal customerNr;

    @Column(name = "FIRSTNAME")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "LASTNAME")
    @Getter
    @Setter
    private String lastName;

    @OneToMany(mappedBy ="customer")
    @Getter
    @Setter
    private List<Transaction> transactions = new ArrayList<Transaction>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTOMERS_ACCOUNTS")
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}