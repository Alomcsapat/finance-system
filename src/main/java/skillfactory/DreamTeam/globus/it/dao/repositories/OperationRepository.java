package skillfactory.DreamTeam.globus.it.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
}
