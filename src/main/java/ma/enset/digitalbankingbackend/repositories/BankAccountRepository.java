package ma.enset.digitalbankingbackend.repositories;

import ma.enset.digitalbankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    List<BankAccount> findAllByCustomerId(Long customer_id);
}
