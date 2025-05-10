package skillfactory.DreamTeam.globus.it.dto.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOperationRequest {
    private Long  accountId;
    private String bankName;
    private String accountNumber;
    private OperationType type;
    private Status status;
    private BigDecimal amount;
    private String  categoryTitle;
    private Long  contactId;
    private String description;
}
