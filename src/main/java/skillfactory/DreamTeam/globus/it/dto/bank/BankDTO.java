package skillfactory.DreamTeam.globus.it.dto.bank;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDTO {
    private final Long id;
    private final String name;
    private final boolean active;
    private final boolean deleted;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;
}
