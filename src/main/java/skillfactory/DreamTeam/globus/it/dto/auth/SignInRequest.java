package skillfactory.DreamTeam.globus.it.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(@NotBlank String email, @NotBlank String password) {
}
