package skillfactory.DreamTeam.globus.it.dao.entities.operation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;

@Getter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public abstract class OperationCategoryEntity extends GeneralEntity {

    @ManyToOne
    private ProfileEntity profile;

    @Setter
    private String title;

}
