package psk.Hibernate.UseCaseControllers;

import psk.Hibernate.DAO.AccountDAO;
import psk.Hibernate.DAO.CustomerDAO;
import psk.Hibernate.DAO.TransactionDAO;
import psk.Hibernate.Entities.Account;
import psk.Hibernate.Entities.Customer;
import psk.Hibernate.Entities.Transaction;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Named
@ApplicationScoped
@Slf4j
public class JpaUseCaseController implements Serializable {
    @Getter
    @Setter
    private Customer selectedCustomer;

    @Getter
    @Setter
    private Account selectedAccount;

    @Getter
    private List<Customer> allCustomers;

    @Getter
    private List<Account> selectedCustomerAccounts;

    @Getter
    private List<Transaction> selectedAccountTransactions;

    @Getter
    @Setter
    private Transaction newTransaction;

    @Getter
    @Setter
    private String toAccNr;

    @PostConstruct
    public void init() {
        loadAllCustomers();
        newTransaction = new Transaction();
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void end() {
        System.out.println(toString() + " destroyed.");
    }

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private CustomerDAO customerDAO;

    @Inject
    private TransactionDAO transactionDAO;

    @Transactional(Transactional.TxType.REQUIRED)
    public void submitTransaction() {
        doTranscation();

        resetTables();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void doTranscation() {
        Account toAccount = accountDAO.getByAccNr(new BigDecimal(toAccNr));
        Account fromAccount = accountDAO.getByAccNr(selectedAccount.getAccountNr());

        newTransaction.setReference(new Timestamp(System.currentTimeMillis()));
        newTransaction.setToAccount(toAccount);
        newTransaction.setFromAccount(fromAccount);

        fromAccount.setBalance(fromAccount.getBalance().subtract(newTransaction.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(newTransaction.getAmount()));

        transactionDAO.create(newTransaction);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void resetTables() {
        newTransaction = new Transaction();

        newTransaction.setCustomer(selectedCustomer);
        selectedCustomerAccounts = customerDAO.getAccountsByCustomerNr(selectedCustomer.getCustomerNr());

        loadTransactionsByCustAndAccNr();
    }

    public void loadAccountsByCustomerNr() {
        selectedAccountTransactions = null;
        selectedAccount = null;
        resetNewTranasction();

        selectedCustomerAccounts = customerDAO.getAccountsByCustomerNr(selectedCustomer.getCustomerNr());
        newTransaction.setCustomer(selectedCustomer);
    }

    public void loadTransactionsByCustAndAccNr() {
        selectedAccountTransactions =
                transactionDAO.getTransactionsByAccAndCust(selectedAccount.getAccountNr(),
                        selectedCustomer.getCustomerNr());
    }

    private void loadAllCustomers(){
        allCustomers = customerDAO.getAllCustomers();
    }

    private void resetNewTranasction() {
        newTransaction.setAmount(null);
        newTransaction.setCustomer(null);
        newTransaction.setId(null);
        newTransaction.setReference(null);
        newTransaction.setToAccount(null);
        newTransaction.setFromAccount(null);
    }
}
