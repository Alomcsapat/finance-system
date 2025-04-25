package skillfactory.DreamTeam.globus.it.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterValidationException extends RuntimeException {

    private final String message;
    private final int systemCode;

}
