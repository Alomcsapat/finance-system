package skillfactory.DreamTeam.globus.it.dto.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.endpoint.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewOperationRequest {
    private Long  accountId;
    private OperationType type;
    private Status status;
    private BigDecimal amount;
    private Long  categoryId;
    private Long  contactId;
    private String description;
}
