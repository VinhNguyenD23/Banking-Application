package vinhnguyen.ptithcm.banking.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vinhnguyen.ptithcm.banking.dto.AccountDto;
import vinhnguyen.ptithcm.banking.repository.JpaAccountRepository;
import vinhnguyen.ptithcm.banking.service.AccountService;
import vinhnguyen.ptithcm.banking.mapper.AccountMapper;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final JpaAccountRepository jpaAccountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        var account = accountMapper.toEntity(accountDto);
        var process = jpaAccountRepository.save(account);
        var result = accountMapper.toDto(process);
        log.info("Create account: {}", account);
        return result;
    }

    @Override
    public AccountDto updateAccount(UUID accountId, double amount) {
        var account = jpaAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(amount);
        var result = jpaAccountRepository.save(account);
        return accountMapper.toDto(result);
    }

    @Override
    public AccountDto getAccount(UUID accountId) {
        var account = jpaAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return accountMapper.toDto(account);
    }

    @Override
    public void deleteAccount(UUID accountId) {
        var account = jpaAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        jpaAccountRepository.delete(account);
    }

    @Override
    public Page<AccountDto> getAccounts(Pageable pageable) {
        var accounts = jpaAccountRepository.findAll(pageable);
        return accounts.map(accountMapper::toDto);
    }

    @Override
    public AccountDto deposit(UUID accountId, double amount)
    {
        var account = jpaAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        var result = jpaAccountRepository.save(account);
        return accountMapper.toDto(result);
    }

    @Override
    public AccountDto withdraw(UUID accountId, double amount) {
        var account = jpaAccountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getBalance() < amount)
        {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        var result = jpaAccountRepository.save(account);
        return accountMapper.toDto(result);
    }

}

