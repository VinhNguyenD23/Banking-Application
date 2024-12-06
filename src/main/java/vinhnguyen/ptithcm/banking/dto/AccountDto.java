package vinhnguyen.ptithcm.banking.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private UUID id;
    private String accountNumber;
    private String accountHolderName;
    private double balance;
}
