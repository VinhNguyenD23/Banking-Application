package vinhnguyen.ptithcm.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinhnguyen.ptithcm.banking.repository.model.Account;

import java.util.List;
import java.util.UUID;

public interface JpaAccountRepository extends JpaRepository<Account, UUID> {

}
