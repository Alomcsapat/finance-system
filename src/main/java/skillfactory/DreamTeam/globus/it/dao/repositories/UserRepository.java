package skillfactory.DreamTeam.globus.it.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.AccountEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByAccountLoginIgnoreCase(String login);

    UserEntity findByAccount(AccountEntity account);

}
