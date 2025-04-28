package skillfactory.DreamTeam.globus.it.services.operation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationCategoryEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.OperationCategoryRepository;
import skillfactory.DreamTeam.globus.it.dao.repositories.OperationRepository;
import skillfactory.DreamTeam.globus.it.dto.operation.CancelOperationRequest;
import skillfactory.DreamTeam.globus.it.dto.operation.ConfirmOperationRequest;
import skillfactory.DreamTeam.globus.it.dto.operation.CreateOperationRequest;
import skillfactory.DreamTeam.globus.it.dto.operation.DeleteOperationRequest;
import skillfactory.DreamTeam.globus.it.services.BankAccountService;
import skillfactory.DreamTeam.globus.it.services.ProfileService;
import skillfactory.DreamTeam.globus.it.enums.Status;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OperationService {
    private OperationRepository operationRepository;
    private BankAccountService bankAccountService;
    private OperationCategoryRepository operationCategoryRepository;
    private ProfileService profileService;

    public OperationEntity createOperation(CreateOperationRequest request) {
        BankAccountEntity bankAccount = bankAccountService.findById(request.getAccountId());
        if (bankAccount == null) {
            System.out.println("bankAccount is null");
        }

        Optional<OperationCategoryEntity> operationCategory = operationCategoryRepository.findById(request.getCategoryId());
        if (operationCategory.isEmpty()) {
            System.out.println("operationCategory is null");
        }

        Optional<ProfileEntity> profile = profileService.findById(request.getContactId());
        if (profile.isEmpty()) {
            System.out.println("profile is null");
        }

        var operation = OperationEntity.builder()
                .account(bankAccount)
                .type(request.getType())
                .status(request.getStatus())
                .amount(request.getAmount())
                .category(operationCategory.get())
                .contact(profile.get())
                .description(request.getDescription())
                .build();
        return operationRepository.save(operation);
    }

    public OperationEntity cancelOperation(CancelOperationRequest request) {
        OperationEntity operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.CANCELLED);
        operationRepository.save(operation);
        return operation;
    }

    public OperationEntity confirmOperation (ConfirmOperationRequest request) {
        OperationEntity operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.CONFIRMED);
        operationRepository.save(operation);
        return operation;
    }

    public OperationEntity deleteOperation (DeleteOperationRequest request) {
        OperationEntity operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.DELETED);
        operationRepository.save(operation);
        return operation;
    }

}
