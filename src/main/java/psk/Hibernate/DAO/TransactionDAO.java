package psk.Hibernate.DAO;

import psk.Hibernate.Entities.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class TransactionDAO {
    @Inject
    private EntityManager em;


    public List<Transaction> getTransactionsByAccAndCust(BigDecimal accountNr, BigDecimal customerNr) {
        Query query = em.createQuery("select c from Transaction c where c.fromAccount.accountNr = :accNr and c.customer.customerNr = :custNr");
        query = query.setParameter("custNr", customerNr);
        query = query.setParameter("accNr", accountNr);

        return query.getResultList();
    }

    public void create(Transaction transaction) {
        em.persist(transaction);
    }
}