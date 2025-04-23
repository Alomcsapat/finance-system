package skillfactory.DreamTeam.globus.it.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    NEW("New"),
    CONFIRMED("Confirmed"),
    PROCESSING("Processing"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    DELETED("Deleted"),
    REFUNDED("Refunded");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
