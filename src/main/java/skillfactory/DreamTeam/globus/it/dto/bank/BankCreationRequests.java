package skillfactory.DreamTeam.globus.it.dto.bank;

import java.math.BigDecimal;

import lombok.Builder;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;

public class BankCreationRequests {
    @Builder
    public record CreateBank(
            String name
    ) {}

    @Builder
    public record CreateBankAccount(
            ProfileEntity holder,
            BankEntity bank,
            BigDecimal balance,
            String accountNumber,
            String title
    ) {}
}
