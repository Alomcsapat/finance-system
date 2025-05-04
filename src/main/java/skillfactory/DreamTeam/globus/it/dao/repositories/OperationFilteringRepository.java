package skillfactory.DreamTeam.globus.it.dao.repositories;

import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dto.operation.OperationQueryParams;


import java.util.List;


@Repository
public interface OperationFilteringRepository  {
    List<OperationEntity> findByFilter(OperationQueryParams object);

    /*@Query("SELECT o FROM OperationEntity o WHERE ((:type is null) or o.type = :type)")
    List<OperationEntity> findAllWithFilters(
            @Param("type") OperationType  type
    );*/
}
