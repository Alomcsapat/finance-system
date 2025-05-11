package skillfactory.DreamTeam.globus.it.dao.repositories;

import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dto.operation.OperationFilter;


import java.util.List;


@Repository
public interface OperationFilteringRepository  {
    List<OperationEntity> findByFilter(OperationFilter object);

    List<OperationEntity> getAllByFilter(OperationFilter filter);
}
