package vinhnguyen.ptithcm.banking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vinhnguyen.ptithcm.banking.dto.AccountDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto updateAccount(UUID accountId, double amount);
    AccountDto getAccount(UUID accountId);
    void deleteAccount(UUID accountId);
    Page<AccountDto> getAccounts(Pageable pageable);
    AccountDto deposit(UUID accountId, double amount);
    AccountDto withdraw(UUID accountId, double amount);
}
