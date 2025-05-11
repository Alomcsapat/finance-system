package skillfactory.DreamTeam.globus.it.dao.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dto.operation.OperationFilter;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OperationRepositoryImpl implements OperationFilteringRepository {
    private final EntityManager entityManager;
    private final OperationCategoryRepository categoryRepository;


    @Override
    public List<OperationEntity> findByFilter(OperationFilter filter) {

        TypedQuery<OperationEntity> query = buildQuery(filter);

        int pageNumber = filter.getPage();
        int pageSize = filter.getSize();
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public List<OperationEntity> getAllByFilter(OperationFilter filter) {
        return buildQuery(filter).getResultList();
    }

    private TypedQuery<OperationEntity> buildQuery(OperationFilter filter) {
        StringBuilder jpql = new StringBuilder("SELECT o FROM OperationEntity o JOIN o.contact c WHERE 1=1");

        if (filter.getType() != null) {
            jpql.append(" AND o.type = :type");
        }

        if (filter.getAccountId() != null) {
            jpql.append(" AND o.account.id = :account");
        }

        if (filter.getCreateDateTime() != null) {
            jpql.append(" AND o.createDateTime  = :create_date_time");
        }

        if (filter.getCreateDateTimeMin() != null) {
            jpql.append(" AND o.createDateTime  >= :create_date_time_min");
        }

        if (filter.getCreateDateTimeMax() != null) {
            jpql.append(" AND o.createDateTime  <= :create_date_time_max");
        }

        if (filter.getStatus() != null) {
            jpql.append(" AND o.status = :status");
        }

        if (filter.getInn() != null) {
            jpql.append(" AND c.inn = :inn");
        }

        if (filter.getAmountMin() != null) {
            jpql.append(" AND o.amount >= :amount_min");
        }
        if (filter.getAmountMax() != null) {
            jpql.append(" AND o.amount <= :amount_max");
        }

        if (filter.getCategoryId() != null) {
            jpql.append(" AND o.category >= :category");
        }

        TypedQuery<OperationEntity> query = entityManager.createQuery(jpql.toString(), OperationEntity.class);

        if (filter.getType() != null) {
            query.setParameter("type", filter.getType());
        }

        if (filter.getAccountId() != null) {
            query.setParameter("account", filter.getAccountId());
        }

        if (filter.getCreateDateTime() != null) {
            query.setParameter("create_date_time", filter.getCreateDateTime());
        }

        if (filter.getCreateDateTimeMin() != null) {
            query.setParameter("create_date_time_min", filter.getCreateDateTimeMin());
        }

        if (filter.getCreateDateTimeMax() != null) {
            query.setParameter("create_date_time_max", filter.getCreateDateTimeMax());
        }

        if (filter.getStatus() != null) {
            query.setParameter("status", filter.getStatus());
        }

        if (filter.getInn() != null) {
            query.setParameter("inn", filter.getInn());
        }

        if (filter.getAmountMin() != null) {
            query.setParameter("amount_min", filter.getAmountMin());
        }

        if (filter.getAmountMax() != null) {
            query.setParameter("amount_max", filter.getAmountMax());
        }

        if (filter.getCategoryId() != null) {
            query.setParameter("category", categoryRepository.getReferenceById(filter.getCategoryId()));
        }
        return query;
    }
}
