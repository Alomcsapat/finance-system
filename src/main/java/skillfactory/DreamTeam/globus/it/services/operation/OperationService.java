package skillfactory.DreamTeam.globus.it.services.operation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationCategoryEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.OperationCategoryRepository;
import skillfactory.DreamTeam.globus.it.dao.repositories.OperationFilteringRepository;
import skillfactory.DreamTeam.globus.it.dto.operation.*;
import skillfactory.DreamTeam.globus.it.enums.Status;
import skillfactory.DreamTeam.globus.it.services.BankAccountService;
import skillfactory.DreamTeam.globus.it.services.ProfileService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OperationService {
    private OperationFilteringRepository operationFilteringRepository;
    private BankAccountService bankAccountService;
    private OperationCategoryRepository operationCategoryRepository;
    private ProfileService profileService;


    public OperationEntity newOperation(NewOperationRequest request) throws InterruptedException {
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
        operation.setStatus(Status.NEW);
        operationFilteringRepository.save(operation);

        Thread.sleep(1500);
        operation.setStatus(Status.PROCESSING);
        operationFilteringRepository.save(operation);

        Thread.sleep(3000);
        if (Math.random() >= 0.1d) {
            operation.setStatus(Status.CONFIRMED);
        } else {
            operation.setStatus(Status.CANCELLED);
        }
        return operationFilteringRepository.save(operation);
    }

    public OperationEntity cancelOperation(CancelOperationRequest request) {
        OperationEntity operation = operationFilteringRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.CANCELLED);
        operationFilteringRepository.save(operation);
        return operation;
    }

    public OperationEntity confirmOperation (ConfirmOperationRequest request) {
        OperationEntity operation = operationFilteringRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.CONFIRMED);
        operationFilteringRepository.save(operation);
        return operation;
    }

    public OperationEntity deleteOperation (DeleteOperationRequest request) {
        OperationEntity operation = operationFilteringRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.DELETED);
        operationFilteringRepository.save(operation);
        return operation;
    }

    public OperationEntity processingOperation (ProcessingOperationRequest request) {
        OperationEntity operation = operationFilteringRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.PROCESSING);
        operationFilteringRepository.save(operation);
        return operation;
    }

    public OperationEntity completedOperation (CompletedOperationRequest request) {
        OperationEntity operation = operationFilteringRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.COMPLETED);
        operationFilteringRepository.save(operation);
        return operation;
    }

    public OperationEntity refundedOperation (RefundedOperationRequest request) {
        OperationEntity operation = operationFilteringRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.REFUNDED);
        operationFilteringRepository.save(operation);
        return operation;
    }

}
