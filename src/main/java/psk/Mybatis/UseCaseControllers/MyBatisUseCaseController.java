package psk.Mybatis.UseCaseControllers;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import psk.Mybatis.mappers.AccountsMapper;
import psk.Mybatis.mappers.CustomersMapper;
import psk.Mybatis.mappers.TransactionsMapper;
import psk.Mybatis.models.Accounts;
import psk.Mybatis.models.Customers;
import psk.Mybatis.models.Transactions;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Named
@ApplicationScoped
@Slf4j
public class MyBatisUseCaseController {
    @Getter
    @Setter
    private List<Customers> allCustomers;

    @Getter
    @Setter
    private List<Transactions> selectedAccountTransactions;

    @Getter
    @Setter
    private Customers selectedCustomer;

    @Getter
    @Setter
    private Accounts selectedAccount;

    @Getter
    @Setter
    private Transactions newTransaction;

    @Getter
    @Setter
    private String toAccNr;

    @Inject
    private AccountsMapper accountsMapper;

    @Inject
    private TransactionsMapper transactionsMapper;

    @Inject
    private CustomersMapper customersMapper;

    @PostConstruct
    public void init() {
        allCustomers = selectCustomers();
        newTransaction = new Transactions();
        System.out.println(toString() + " constructed. (MyBatis)");
    }

    public List<Customers> selectCustomers() {
        return customersMapper.selectAllCustomers();
    }

    public void customerSelectedAction() {
        resetVariables();

        newTransaction.setCustomerId(selectedCustomer.getId());
    }

    public void accountSelectedAction() {
        selectedAccountTransactions = transactionsMapper.selectTransfersByCustomer(selectedAccount.getId(), selectedCustomer.getId());

        newTransaction.setFromaccid(selectedAccount.getId());
    }

    @Transactional
    public void submitTransaction() {
        performTransaction();

        resetTables();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void performTransaction() {
        newTransaction.setReference(new Timestamp(System.currentTimeMillis()));
        newTransaction.setToaccid(accountsMapper.selectIdByAccountNr(new Long(toAccNr)));

        transactionsMapper.insert(newTransaction);

        Accounts fromAccount = accountsMapper.selectByPrimaryKey(newTransaction.getFromaccid());
        fromAccount.setBalance(fromAccount.getBalance().subtract(newTransaction.getAmount()));
        accountsMapper.updateByPrimaryKey(fromAccount);

        Accounts toAccount = accountsMapper.selectByPrimaryKey(newTransaction.getToaccid());
        toAccount.setBalance(toAccount.getBalance().add(newTransaction.getAmount()));
        accountsMapper.updateByPrimaryKey(toAccount);

        newTransaction = new Transactions();
    }

    private void resetVariables() {
        selectedAccount = null;
        selectedAccountTransactions = null;
        resetNewTranasction();
    }

    private void resetTables() {
        allCustomers = selectCustomers();

        if(selectedCustomer != null) {
            for(Customers customer : allCustomers) {
                if(selectedCustomer.getId() == customer.getId())
                {
                    selectedCustomer = customer;
                    break;
                }
            }

            newTransaction.setCustomerId(selectedCustomer.getId());

            if(selectedAccount != null) {
                for(Accounts account : selectedCustomer.getAccountList()) {
                    if(selectedAccount.getId() == account.getId())
                    {
                        selectedAccount = account;
                        break;
                    }
                }

                accountSelectedAction();
            }
        }
    }

    private void resetNewTranasction() {
        newTransaction.setAmount(null);
        newTransaction.setCustomer(null);
        newTransaction.setCustomerId(null);
        newTransaction.setFromaccid(null);
        newTransaction.setFromAccount(null);
        newTransaction.setId(null);
        newTransaction.setReference(null);
        newTransaction.setToaccid(null);
        newTransaction.setToAccount(null);
    }
}
