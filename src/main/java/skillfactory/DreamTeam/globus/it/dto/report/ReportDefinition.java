package skillfactory.DreamTeam.globus.it.dto.report;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
public record ReportDefinition(String holderName, String holderInn, List<Item> items) {

    @Data
    @Builder
    @RequiredArgsConstructor
    public static class Item {
        private final HolderType contactType;
        private final OperationType type;
        private final LocalDateTime createDateTime;
        private final String description;
        private final String amount;
        private final Status status;
        private final String sendBank;
        private final String recipientBank;
        private final String sendBankAccount;
        private final String recipientBankAccount;
        private final String inn;
        private final String category;
        private final String recipientNumber;

        public String getCreateDateTime() {
            return createDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        }


    }

    @Getter
    @RequiredArgsConstructor
    public enum HolderType {
        INDIVIDUAL("Физическое"),
        COMPANY("Юридическое");

        private final String title;
    }
}
