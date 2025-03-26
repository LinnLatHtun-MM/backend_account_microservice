package per.llt.account.service;

import per.llt.account.dto.CustomerDto;

public interface IAccountService {

    /**
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - String
     */
    CustomerDto fetchAccounts(String mobileNumber);


    /**
     * @param customerDto - CustomerDto Object
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - String
     */
    boolean deleteAccount(String mobileNumber);

}
