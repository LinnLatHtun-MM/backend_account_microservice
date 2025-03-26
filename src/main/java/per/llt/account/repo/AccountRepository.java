package per.llt.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import per.llt.account.entity.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(Long accountNumber);

    List<Account> findByCustomer_CustomerId(Long customerId);

    void deleteAccountByCustomer_CustomerId(Long customerId);

}
