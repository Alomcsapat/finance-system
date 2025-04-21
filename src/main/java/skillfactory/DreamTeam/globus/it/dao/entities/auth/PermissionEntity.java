package skillfactory.DreamTeam.globus.it.dao.entities.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import skillfactory.DreamTeam.globus.it.constants.security.Role;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.AccountEntity;

@Entity
@SuperBuilder
@Getter
@Table(name = "permissions")
@AllArgsConstructor
public class PermissionEntity extends GeneralEntity {

    @ManyToOne
    private AccountEntity account;

    @Builder.Default
    private Role role = Role.USER;


    public PermissionEntity() {
        role = Role.USER;
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PermissionEntity that = (PermissionEntity) obj;
        return role == that.role;
    }
}
