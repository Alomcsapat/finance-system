package skillfactory.DreamTeam.globus.it.dao.entities.operation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;

@Getter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class OperationCategoryEntity extends GeneralEntity {
    @Setter
    private String title;
}