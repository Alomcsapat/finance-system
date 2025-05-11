package skillfactory.DreamTeam.globus.it.services.operation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankAccountEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.bank.BankEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationCategoryEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.operation.OperationEntity;
import skillfactory.DreamTeam.globus.it.dao.entities.profiles.AccountEntity;
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
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OperationService {
    @Autowired

    AuthService authService;
    private BankService bankService;
    private OperationRepository operationRepository;
    private BankAccountService bankAccountService;
    private OperationCategoryRepository operationCategoryRepository;
    private ProfileService profileService;
    private OperationCategoryService operationCategoryService;

    public List<OperationEntity> operations(OperationQueryParams filters){
        return operationRepository.findByFilter(filters);
    }

    public OperationEntity createOperation(CreateOperationRequest request) throws InterruptedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WalletUserDetails userDetails = (WalletUserDetails) authentication.getPrincipal();
        
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

        Optional<ProfileEntity> profile = profileService.findById(userDetails.getUserId());
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
