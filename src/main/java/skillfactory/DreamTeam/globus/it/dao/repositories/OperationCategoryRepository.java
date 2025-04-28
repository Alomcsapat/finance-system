package skillfactory.DreamTeam.globus.it.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationCategoryEntity;


@Repository
public interface OperationCategoryRepository  extends JpaRepository<OperationCategoryEntity, Long> {
    public OperationCategoryEntity findById(long id);
}
