package skillfactory.DreamTeam.globus.it.dao.entities.profiles;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "contacts")
public abstract class ContactEntity extends ProfileEntity {

}
