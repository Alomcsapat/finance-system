package skillfactory.DreamTeam.globus.it.dao.entities.bank;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;

@Data
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
    private String accountNumber;
    private String title;
    //private List<OperationEntity> logs;
}
