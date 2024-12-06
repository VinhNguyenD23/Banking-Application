package vinhnguyen.ptithcm.banking.service;

import vinhnguyen.ptithcm.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto updateAccount(AccountDto accountDto);
    AccountDto getAccount(String accountId);
    void deleteAccount(String accountId);
    List<AccountDto> getAccounts();
}
