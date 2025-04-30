package skillfactory.DreamTeam.globus.it.dto.operation;


import lombok.Builder;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;

@Data
@Builder
public class OperationDTO {
    private final Long accountId;
    private final OperationType type;
    private final Status status;
    private final BigDecimal amount;
    private final String categoryTitle;
    private final Long contactId;
    private final String description;
}
