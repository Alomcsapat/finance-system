package skillfactory.DreamTeam.globus.it.dao.entities.profiles;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
    @Size(min = 10, max = 12)
    private String inn;

    @Setter
    @Email
    private String email;

    @Setter
    private String phone;

}
