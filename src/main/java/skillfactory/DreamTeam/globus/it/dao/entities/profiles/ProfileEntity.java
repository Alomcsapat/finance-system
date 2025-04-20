package skillfactory.DreamTeam.globus.it.dao.entities.profiles;


import jakarta.persistence.Entity;
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
public abstract class ProfileEntity extends GeneralEntity {

    protected String name;

    @Setter
    private String inn;

    @Setter
    private String email;

    @Setter
    private String phone;

}
