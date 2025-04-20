package skillfactory.DreamTeam.globus.it.dao.entities.profiles;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;

@Getter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProfileEntity extends GeneralEntity {

    protected String name;

    @Setter
    private String inn;

    @Setter
    private String email;

    @Setter
    private String phone;

}
