package skillfactory.DreamTeam.globus.it.dto.profile;

import lombok.Builder;

public class ProfileCreationRequests {

    @Builder
    public record CreateUser(
            String name,
            String inn,
            String email,
            String phone,
            String login,
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
