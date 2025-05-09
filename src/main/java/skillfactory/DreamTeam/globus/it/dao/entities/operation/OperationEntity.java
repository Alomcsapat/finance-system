package skillfactory.DreamTeam.globus.it.dao.entities.operation;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "getPageByFilter")
public class OperationEntity extends GeneralEntity {
    @ManyToOne
    private BankAccountEntity account;

    @Enumerated(EnumType.STRING)
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