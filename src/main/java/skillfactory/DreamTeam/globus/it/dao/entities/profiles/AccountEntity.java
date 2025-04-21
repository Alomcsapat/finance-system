package skillfactory.DreamTeam.globus.it.dao.entities.profiles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import skillfactory.DreamTeam.globus.it.dao.entities.GeneralEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.auth.PermissionEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@NamedEntityGraph(
        name = "user",
        attributeNodes = @NamedAttributeNode("user")
)

public class AccountEntity extends GeneralEntity {

    private String login;

    private String password;

    @OneToOne(mappedBy = "account")
    private UserEntity user;

    @Builder.Default
    @Where(clause = "active = true")
    @OneToMany(mappedBy = "account", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<PermissionEntity> permissions = new HashSet<>() {{ add(new PermissionEntity()); }};

}
