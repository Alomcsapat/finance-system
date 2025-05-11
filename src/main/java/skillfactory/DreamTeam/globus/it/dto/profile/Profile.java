package skillfactory.DreamTeam.globus.it.dto.profile;

import lombok.Builder;

@Builder
public record Profile(
        Long id,
        String name,
        String inn,
        String email,
        String phone
) {}
