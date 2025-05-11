package skillfactory.DreamTeam.globus.it.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationCategoryEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.OperationCategoryRepository;
import skillfactory.DreamTeam.globus.it.dto.operation.CreateCategoryRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationCategoryService {
    private final OperationCategoryRepository operationCategoryRepository;

    public OperationCategoryEntity createOperationCategory(CreateCategoryRequest request) {
        return operationCategoryRepository.save(
            OperationCategoryEntity.builder()
            .title(request.title)
            .build()
        );
    }
}
