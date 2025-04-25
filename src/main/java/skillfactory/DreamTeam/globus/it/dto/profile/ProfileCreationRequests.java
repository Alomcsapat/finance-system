package skillfactory.DreamTeam.globus.it.dto.profile;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public class ProfileCreationRequests {

    @Builder
    public record CreateUser(
            String name,
            @Nullable
            @Size(min = 10, max = 12)
            String inn,
            @Email
            String email,
            String phone,
            @NotBlank
            String login,
            @NotBlank
            String password
    ) {}

    @Builder
    public record CreatePerson(
            String firstName,
            String lastName,
            String middleName,
            String inn,
            String email,
            String phone
    ) {}

    @Builder
    public record CreateCompany(
            String name,
            String inn,
            String email,
            String phone
    ) {}
}
