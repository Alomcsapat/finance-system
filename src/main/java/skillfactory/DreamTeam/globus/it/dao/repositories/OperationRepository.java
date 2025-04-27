package skillfactory.DreamTeam.globus.it.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationCategoryEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.enums.OperationType;
import skillfactory.DreamTeam.globus.it.enums.Status;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    List<OperationEntity> findByType(OperationType type);
    List<OperationEntity> findByStatus(Status status);
    List<OperationEntity> findByCategory(OperationCategoryEntity category);
    List<OperationEntity> findByAmount(BigDecimal amount);
    //List<OperationEntity> findByInn(String inn);
}