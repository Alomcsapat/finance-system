package skillfactory.DreamTeam.globus.it.dao.entities.profiles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;

import java.util.Arrays;
import java.util.stream.Collectors;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@Table(name = "users")
@RequiredArgsConstructor
public class UserEntity extends ProfileEntity {

    @Immutable
    @OneToOne(cascade = CascadeType.ALL)
    private AccountEntity account;

    public void setName(String name) {
        this.name = name;
    }

    @PrePersist
    public void normalizeData() {
        this.name = Arrays.stream(name.split("\\s+"))
                .map(it -> it.substring(0, 1).toUpperCase() + it.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

}
