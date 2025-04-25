package skillfactory.DreamTeam.globus.it.dto.bank;

import java.math.BigDecimal;

import lombok.Builder;

public class BankCreationRequests {
    @Builder
    public record CreateBank(
            String name
    ) {}

    @Builder
    public record CreateBankAccount(
            Long holderId,
            Long bankId,
            BigDecimal balance,
            String accountNumber,
            String title
    ) {}
}
