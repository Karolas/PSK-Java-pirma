package Hibernate.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
@EqualsAndHashCode(of = "reference")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "REFERENCE")
    private Timestamp reference;

    @Column(name = "FROMACCNR")
    private DecimalFormat fromAccNr;

    @Column(name = "TOACCNR")
    private DecimalFormat toAccNr;

    @Column(name = "AMOUNT")
    private DecimalFormat amount;

    @Column(name = "ORDERERCUSTNR")
    private DecimalFormat orderreCustNr;

    @ManyToOne
    private Customer customer;
}
