package skillfactory.DreamTeam.globus.it.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OperationType {
    CREDIT("Credit"),
    DEBIT("Debit");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
