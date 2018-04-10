package psk.Hibernate.DAO;

import psk.Hibernate.Entities.Account;
import psk.Hibernate.Entities.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@ApplicationScoped
public class AccountDAO {
    @Inject
    private EntityManager em;

    public Account getByAccNr(BigDecimal accountNr) {
        return em.createQuery("SELECT c from Account c where c.accountNr = :accNr", Account.class)
                .setParameter("accNr", accountNr).getSingleResult();
    }
}
