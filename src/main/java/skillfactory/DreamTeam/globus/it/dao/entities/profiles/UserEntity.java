package skillfactory.DreamTeam.globus.it.dao.entities.profiles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@Table(name = "users")
@RequiredArgsConstructor
public class UserEntity extends ProfileEntity {

    @Immutable
    @OneToOne(cascade = {CascadeType.PERSIST})
    private AccountEntity account;

    public void setName(String name) {
        this.name = name;
    }

}
