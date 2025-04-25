package skillfactory.DreamTeam.globus.it.constants.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemCode {
    NOT_UNIQUE(4001, 400),

    USER_ALREADY_EXISTS(4002, 400),

    BAD_CREDENTIALS(4011, 401),
    NOT_FOUND(404, 404),
    USER_NOT_FOUND(4041, 404),
    USER_NOT_FOUND_BY_ID(4042, 404),
    INVALID_HTTP_RESPONSE(5001, 500);


    private int systemCode;
    private int httpCode;

}