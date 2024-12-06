package vinhnguyen.ptithcm.banking.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vinhnguyen.ptithcm.banking.dto.AccountDto;
import vinhnguyen.ptithcm.banking.repository.JpaAccountRepository;
import vinhnguyen.ptithcm.banking.service.AccountService;
import vinhnguyen.ptithcm.banking.mapper.AccountMapper;

import java.util.List;

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
    public AccountDto updateAccount(AccountDto accountDto) {
        return null;
    }

    @Override
    public AccountDto getAccount(String accountId) {
        return null;
    }

    @Override
    public void deleteAccount(String accountId) {

    }

    @Override
    public List<AccountDto> getAccounts() {
        return List.of();
    }
}
