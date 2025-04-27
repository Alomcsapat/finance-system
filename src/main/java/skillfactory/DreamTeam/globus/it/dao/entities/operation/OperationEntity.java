package skillfactory.DreamTeam.globus.it.dao.entities.operation;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.actuate.endpoint.OperationType;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;

@Getter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operations")
public class OperationEntity extends GeneralEntity {
    @ManyToOne
    private BankAccountEntity account;

    @Setter
    private OperationType type;

    @Setter
    private Status status;

    @Setter
    private BigDecimal amount;

    @ManyToOne
    private OperationCategoryEntity category;

    @ManyToOne
    private ProfileEntity contact;

    @Setter
    private String description;

}
