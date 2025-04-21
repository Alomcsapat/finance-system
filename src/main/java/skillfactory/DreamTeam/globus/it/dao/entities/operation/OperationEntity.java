package skillfactory.DreamTeam.globus.it.dao.entities.operation;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.actuate.endpoint.OperationType;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;

import java.math.BigDecimal;

@Getter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operations")
public class OperationEntity extends ProfileEntity {
    @ManyToOne
    private BankAccount account;

    @Setter
    private OperationType type;

    @Setter
    private OperationStatus status;

    @Setter
    private BigDecimal amount;

    @ManyToOne
    private OperationCategoryEntity category;

    @ManyToOne
    private ProfileEntity contact;

    @Setter
    private String description;

}
