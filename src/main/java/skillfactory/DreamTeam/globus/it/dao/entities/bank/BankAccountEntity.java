package skillfactory.DreamTeam.globus.it.dao.entities.bank;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_accounts")
public class BankAccountEntity extends GeneralEntity {
    @OneToOne
    private ProfileEntity holder;
    @ManyToOne
    private BankEntity bank;
    private BigDecimal balance;
    @Builder.Default
    private String accountNumber = UUID.randomUUID().toString();
    private String title;
    //private List<OperationEntity> logs;
}
