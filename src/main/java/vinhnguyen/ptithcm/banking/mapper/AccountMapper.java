package vinhnguyen.ptithcm.banking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vinhnguyen.ptithcm.banking.dto.AccountDto;
import vinhnguyen.ptithcm.banking.repository.model.Account;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    Account toEntity(AccountDto accountDto);
    AccountDto toDto(Account account);

}
