package psk.Hibernate.DAO;

import psk.Hibernate.Entities.Account;
import psk.Hibernate.Entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class CustomerDAO {
    @Inject
    private EntityManager em;

    public List<Customer> getAllCustomers() {
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public List<Account> getAccountsByCustomerNr(BigDecimal customerNr) {
        return em.createQuery("select c.accounts from Customer c where c.customerNr = :custNr")
                .setParameter("custNr", customerNr).getResultList();
    }
}
