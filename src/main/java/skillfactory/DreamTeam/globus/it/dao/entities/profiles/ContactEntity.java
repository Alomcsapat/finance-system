package skillfactory.DreamTeam.globus.it.dao.entities.profiles;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "contacts")
@SuperBuilder
@NoArgsConstructor
public abstract class ContactEntity extends ProfileEntity {
}
