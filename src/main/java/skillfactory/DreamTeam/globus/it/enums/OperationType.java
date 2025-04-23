package skillfactory.DreamTeam.globus.it.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OperationType {
    CREDIT("Credit"),
    DEBIT("Debit");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
