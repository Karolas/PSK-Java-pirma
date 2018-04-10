package psk.Mybatis.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Customers {
    private Integer id;
    private Long customernr;
    private String firstname;
    private String lastname;

    private List<Accounts> accountList;
}