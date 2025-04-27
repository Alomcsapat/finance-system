package skillfactory.DreamTeam.globus.it.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    NEW("New"),
    CONFIRMED("Confirmed"),
    PROCESSING("Processing"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed"),
    DELETED("Deleted"),
    REFUNDED("Refunded");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
