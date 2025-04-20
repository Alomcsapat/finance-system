package skillfactory.DreamTeam.globus.it.dao.entities.profiles;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class ContactEntity extends ProfileEntity {
}
