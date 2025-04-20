package skillfactory.DreamTeam.globus.it.dto.profile;

import lombok.Builder;
import org.springframework.lang.Nullable;

public class ProfileCreationRequests {

    public interface Profile {

        @Nullable
        String inn();

        @Nullable
        String email();

        @Nullable
        String phone();

    }

    @Builder
    public record User(
            String name,
            String inn,
            String email,
            String phone,
            String login,
            String password
    ) implements Profile { }

    @Builder
    public record Person(
            String firstName,
            String lastName,
            String middleName,
            String inn,
            String email,
            String phone
    ) implements Profile { }

    @Builder
    public record Company(
            String name,
            String inn,
            String email,
            String phone
    ) implements Profile { }
}
