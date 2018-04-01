package Hibernate.UseCaseControllers;

import Hibernate.DAO.AccountDAO;
import Hibernate.Entities.Account;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@Slf4j
public class AccountUCC {
    @Getter
    private long accCount = -1;

    @PostConstruct
    public void init() {
        loadAccCount();
    }

    @Inject
    private AccountDAO accountDAO;

    private void loadAccCount() {
        accCount = accountDAO.getAccountsCount();
    }
}
