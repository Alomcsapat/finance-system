package skillfactory.DreamTeam.globus.it.services.operation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationCategoryEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.ProfileEntity;
import skillfactory.DreamTeam.globus.it.dao.repositories.OperationCategoryRepository;
import skillfactory.DreamTeam.globus.it.dao.repositories.OperationRepository;
import skillfactory.DreamTeam.globus.it.dto.auth.WalletUserDetails;
import skillfactory.DreamTeam.globus.it.dto.bank.BankCreationRequests;
import skillfactory.DreamTeam.globus.it.dto.operation.*;
import skillfactory.DreamTeam.globus.it.enums.Status;
import skillfactory.DreamTeam.globus.it.services.BankAccountService;
import skillfactory.DreamTeam.globus.it.services.BankService;
import skillfactory.DreamTeam.globus.it.services.OperationCategoryService;
import skillfactory.DreamTeam.globus.it.services.ProfileService;
import skillfactory.DreamTeam.globus.it.services.auth.AuthService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final AuthService authService;
    private final BankService bankService;
    private final OperationRepository operationRepository;
    private final BankAccountService bankAccountService;
    private final OperationCategoryRepository operationCategoryRepository;
    private final ProfileService profileService;
    private final OperationCategoryService operationCategoryService;

    public List<OperationEntity> getPageByFilter(OperationFilter filters){
        return operationRepository.findByFilter(filters);
    }

    public List<OperationEntity> getAllByFilter(OperationFilter filters){
        return operationRepository.getAllByFilter(filters);
    }

    public OperationEntity createOperation(CreateOperationRequest request, WalletUserDetails userDetails) throws InterruptedException {
        BankAccountEntity bankAccount = bankAccountService.findById(request.getAccountId());

        if (bankAccount == null) {
            var bank = bankService.createBank(new BankCreationRequests.CreateBank(request.getBankName()));
            bankAccount = bankAccountService.createAccount(new BankCreationRequests.CreateBankAccount(userDetails.getUserId(),
            bank.getId(), request.getAmount(), request.getAccountNumber(), ""));
        }

        OperationCategoryEntity operationCategory = operationCategoryRepository.findByTitle(request.getCategoryTitle());
        if (operationCategory == null) {
            operationCategory = operationCategoryService.createOperationCategory(new CreateCategoryRequest(request.getCategoryTitle()));
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
                .category(operationCategory)
                .contact(profile.get())
                .description(request.getDescription())
                .build();
        operation.setStatus(Status.NEW);
        operationRepository.save(operation);

        Thread.sleep(1500);
        operation.setStatus(Status.PROCESSING);
        operationRepository.save(operation);

        Thread.sleep(3000);
        if (Math.random() >= 0.1d) {
            operation.setStatus(Status.CONFIRMED);
        } else {
            operation.setStatus(Status.CANCELLED);
        }
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

    public OperationEntity processingOperation (ProcessingOperationRequest request) {
        OperationEntity operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.PROCESSING);
        operationRepository.save(operation);
        return operation;
    }

    public OperationEntity completedOperation (CompletedOperationRequest request) {
        OperationEntity operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.COMPLETED);
        operationRepository.save(operation);
        return operation;
    }

    public OperationEntity refundedOperation (RefundedOperationRequest request) {
        OperationEntity operation = operationRepository.findById(request.getOperationId())
                .orElseThrow(() -> new NoSuchElementException("Operation not found with id: " + request.getOperationId()));
        operation.setStatus(Status.REFUNDED);
        operationRepository.save(operation);
        return operation;
    }

}
