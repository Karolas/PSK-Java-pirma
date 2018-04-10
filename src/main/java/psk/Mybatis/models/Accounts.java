package psk.Mybatis.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Accounts {
    private Integer id;
    private Long accountnr;
    private BigDecimal balance;
}