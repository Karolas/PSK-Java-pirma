package Hibernate.DAO;

import Hibernate.Entities.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AccountDAO {
    @Inject
    private EntityManager em;

    public List<Account> getAllAccounts() {
        return em.createNamedQuery("Account.findAll", Account.class).getResultList();
    }

    public long getAccountsCount() {
        return ((Long) em.createNamedQuery("Account.count", Object.class).getSingleResult());
    }
}
