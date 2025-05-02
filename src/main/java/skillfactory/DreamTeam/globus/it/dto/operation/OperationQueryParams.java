package skillfactory.DreamTeam.globus.it.dto.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationQueryParams {
    private OperationType type;
    private Long accountId;
    private LocalDateTime createDateTime;
    private LocalDateTime createDateTimeMin;
    private LocalDateTime createDateTimeMax;
    private Status status;
    private String inn;
    private BigDecimal amountMin;
    private BigDecimal amountMax;
    private Long categoryId;

}
