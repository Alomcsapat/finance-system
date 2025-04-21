package skillfactory.DreamTeam.globus.it.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {

    private final String token;
    private final Long accountId;
    private final Long userId;

}
