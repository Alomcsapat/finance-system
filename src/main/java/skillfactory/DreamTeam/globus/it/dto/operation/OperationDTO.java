package skillfactory.DreamTeam.globus.it.dto.operation;


import lombok.Builder;
import lombok.Data;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OperationDTO {
    private final Long accountId;
    private final OperationType type;
    private LocalDateTime createDateTime;
    private LocalDateTime createDateTimeMin;
    private LocalDateTime createDateTimeMax;
    private final Status status;
    private final String inn;
    private final BigDecimal amount;
    private final String categoryTitle;
    private final Long contactId;
    private final String description;
}
