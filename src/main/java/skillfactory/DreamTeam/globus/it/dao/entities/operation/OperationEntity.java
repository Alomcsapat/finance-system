package skillfactory.DreamTeam.globus.it.dao.entities.operation;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.actuate.endpoint.OperationType;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operations")
public class OperationEntity extends GeneralEntity {
    @ManyToOne
    private BankAccountEntity account;

    private OperationType type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal amount;

    @ManyToOne
    private OperationCategoryEntity category;

    @ManyToOne
    private ProfileEntity contact;

    private String description;

}