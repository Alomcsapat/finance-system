package skillfactory.DreamTeam.globus.it.dao.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@Where(clause = "deleted = false")
public class GeneralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_id_seq")
    @SequenceGenerator(name = "wallet_id_seq", sequenceName = "wallet_id_seq", allocationSize = 1)
    private Long id;

    @Setter
    private Boolean active;

    @Setter
    private Boolean deleted;

    private final LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    public GeneralEntity() {
        this.active = true;
        this.deleted = false;
        this.createDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDateTime = LocalDateTime.now();
    }


}
