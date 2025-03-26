package per.llt.account.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import per.llt.account.dto.AccountDto;
import per.llt.account.exception.ResourceNotFoundException;
import per.llt.account.mapper.AccountMapper;
import per.llt.account.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import per.llt.account.constants.AccountConstants;
import per.llt.account.dto.CustomerDto;
import per.llt.account.entity.Account;
import per.llt.account.entity.Customer;
import per.llt.account.exception.CustomerAlreadyException;
import per.llt.account.repo.AccountRepository;
import per.llt.account.repo.CustomerRepository;
import per.llt.account.service.IAccountService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyException("Customer already registered with the given phoneNumber: " + customerDto.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
//        Automatically do auditAwareImpl to set with the base entity
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("System");
        Account account = createNewAccount();
//        account.setCreatedAt(LocalDateTime.now());
//        account.setCreatedBy("System");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        customer.setAccounts(accounts);
        account.setCustomer(customer);
        customerRepository.save(customer);
    }


    /**
     * Create a new account
     */
    private Account createNewAccount() {

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        Account newAccount = new Account();
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("System");
        return newAccount;
    }


    @Override
    public CustomerDto fetchAccounts(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        List<Account> accounts = customer.getAccounts();
        if (accounts == null || accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString());
        }

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountDto accountDto = AccountMapper.mapToAccountDto(accounts.get(0), new AccountDto());
        customerDto.setAccountDto(accountDto);

        return customerDto;

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;

        AccountDto accountDto = customerDto.getAccountDto();

        if (accountDto != null) {
            Account account = accountRepository.findByAccountNumber(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountDto.getAccountNumber().toString()));

            AccountMapper.mapToAccount(accountDto, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomer().getCustomerId();
            Customer customer = customerRepository.findByCustomerId(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);

            isUpdated = true;

        }
        return isUpdated;
    }


    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of account detail is successful or not
     */
    @Modifying
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        try {
            customerRepository.deleteByCustomerId(customer.getCustomerId());
            accountRepository.deleteAccountByCustomer_CustomerId(customer.getCustomerId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
