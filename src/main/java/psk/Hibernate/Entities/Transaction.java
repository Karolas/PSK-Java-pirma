package psk.Hibernate.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
@EqualsAndHashCode(of = "reference")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "REFERENCE")
    private Timestamp reference;

    @Column(name = "AMOUNT")
    @Digits(integer = 11, fraction = 2)
    private BigDecimal amount;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="FROMACCID")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name="TOACCID")
    private Account toAccount;
}
