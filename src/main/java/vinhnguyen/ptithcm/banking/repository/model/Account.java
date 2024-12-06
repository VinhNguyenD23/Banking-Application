package vinhnguyen.ptithcm.banking.repository.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account", schema = "banking_user")
public class Account extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @NotBlank
    @Column(name = "account_number")
    private String accountNumber;
    @NotNull
    @NotBlank
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @NotNull
    @Column(name = "balance")
    private double balance;
}
