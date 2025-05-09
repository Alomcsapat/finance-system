package skillfactory.DreamTeam.globus.it.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankEntity;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Long>{
    Optional<BankEntity> findByNameIgnoreCase(@Param("name") String name);
}
