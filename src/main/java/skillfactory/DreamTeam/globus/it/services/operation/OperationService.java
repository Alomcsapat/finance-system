package skillfactory.DreamTeam.globus.it.services.operation;

import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.OperationRepository;
import skillfactory.DreamTeam.globus.it.dto.operation.CancelOperationRequest;
import skillfactory.DreamTeam.globus.it.dto.operation.ConfirmOperationRequest;
import skillfactory.DreamTeam.globus.it.dto.operation.CreateOperationRequest;
import skillfactory.DreamTeam.globus.it.dto.operation.DeleteOperationRequest;
import skillfactory.DreamTeam.globus.it.services.BankService;
import skillfactory.DreamTeam.globus.it.services.ProfileService;
import skillfactory.DreamTeam.globus.it.enums.Status;
import java.util.NoSuchElementException;

public class OperationService {
    private OperationRepository operationRepository;
    private ProfileService profileService;
    private BankService bankService;

    public OperationEntity createOperation(CreateOperationRequest request) {
        var operation = OperationEntity.builder()
                .account(request.getAccount())
                .type(request.getType())
                .status(request.getStatus())
                .amount(request.getAmount())
                .category(request.getCategory())
                .contact(request.getContact())
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

    public OperationEntity ConfirmOperation (ConfirmOperationRequest request) {
        OperationEntity operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.CONFIRMED);
        operationRepository.save(operation);
        return operation;
    }

    public OperationEntity DeleteOperation (DeleteOperationRequest request) {
        OperationEntity operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.DELETED);
        operationRepository.save(operation);
        return operation;
    }

}
