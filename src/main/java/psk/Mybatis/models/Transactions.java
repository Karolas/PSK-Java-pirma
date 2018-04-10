package psk.Mybatis.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Transactions {
    private Integer id;
    private Date reference;
    private Integer fromaccid;
    private Integer toaccid;
    private BigDecimal amount;
    private Integer customerId;

    private Accounts fromAccount;
    private Accounts toAccount;
    private Customers customer;
}