package skillfactory.DreamTeam.globus.it.dao.entities.bank;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "banks")
public class BankEntity extends GeneralEntity {
    @Column(nullable = false)
    private String name;
    public void setName(String name) {
        this.name = name;
    }
}
