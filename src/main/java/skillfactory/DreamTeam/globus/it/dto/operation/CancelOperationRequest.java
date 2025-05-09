package skillfactory.DreamTeam.globus.it.dto.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CancelOperationRequest {
    private Long operationId;
}
