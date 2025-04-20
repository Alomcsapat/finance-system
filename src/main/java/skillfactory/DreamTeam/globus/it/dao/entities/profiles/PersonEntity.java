package skillfactory.DreamTeam.globus.it.dao.entities.profiles;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

@Entity
@SuperBuilder
@Table(name = "persons")
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity extends ContactEntity {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @Nullable
    private String middleName;

    public String resolveFullName() {
        StringBuilder builder = new StringBuilder();
        builder.append(lastName);
        builder.append(" ").append(firstName);
        if (middleName != null && !middleName.isBlank()) {
            builder.append(" ").append(middleName);
        }
        return builder.toString();
    }

    @PreUpdate
    @PrePersist
    public void setFullName() {
        name = resolveFullName();
    }
}
