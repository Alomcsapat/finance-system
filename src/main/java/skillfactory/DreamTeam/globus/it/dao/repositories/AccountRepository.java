package skillfactory.DreamTeam.globus.it.dao.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.AccountEntity;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @EntityGraph("user")
    Optional<AccountEntity> findByLoginIgnoreCase(String username);
}
